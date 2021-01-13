package com.example.susspropertyapp.ui.chats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.AgentDataManager;
import com.example.susspropertyapp.model.ChatRoom;
import com.example.susspropertyapp.model.ChatRoomDataManager;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingDataManager;
import com.example.susspropertyapp.model.MyMsg;
import com.example.susspropertyapp.model.MyMsgAdapter;
import com.example.susspropertyapp.model.MyMsgDataManager;
import com.example.susspropertyapp.model.UserDataManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatDetailFragment extends Fragment {
    View root;
    Toolbar topAppBar;

    RecyclerView mMsgRecyclerView;

    private AgentDataManager agentDM;
    private UserDataManager loginDM;
    private MyMsgDataManager msgDM;
    RecyclerView.Adapter adapter;
    ArrayList<MyMsg> mMsgList;
    ChatRoom chatRoom;
    String roomId;
    String roomId2;
    String listingName;
    String userName = "";
    Boolean isSeller = false;

    public ChatDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_chat_detail, null);
        topAppBar = root.findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(v -> {
//            AppCompatActivity activity = (AppCompatActivity) root.getContext();
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_chatDetailFragment_to_navigation_chats);
        });

        TextView tvUserName = root.findViewById(R.id.tvUserName);
        TextView tvListingName = root.findViewById(R.id.tvListingName);

        // new enquire page > create new chatroom & msg objs
        // on enter add to sql
        Bundle bundle = this.getArguments();

        Agent agent = (Agent) bundle.getSerializable("agent");
        Listing listing = (Listing) bundle.getSerializable("listing");
        chatRoom = (ChatRoom) bundle.getSerializable("chatroom"); // does not come with meeting
        String meeting = bundle.getString("meeting");
        roomId = bundle.getString("roomId");

        if (agent != null) {
            Log.i("info","coming from agent enquire");
            tvUserName.setText("");
            tvListingName.setText(agent.getName());
            userName = agent.getId();
            listingName = "default";
            chatRoom = new ChatRoom();
            chatRoom.setChatRoom(userName,MainActivity.userId,listingName);
            roomId = userName+"_"+MainActivity.userId+"_"+ listingName;
            roomId2 = MainActivity.userId+"_"+userName+ "_"+listingName;
        } else if (listing != null) {
            Log.i("info","coming from listing detail");
            userName = listing.getAgentId();
            agentDM = new AgentDataManager(getActivity());
            chatRoom = new ChatRoom();
            chatRoom.setChatRoom(userName,MainActivity.userId,listingName);

            Cursor c = agentDM.searchRecord(userName);
            if (c.moveToNext())  userName = c.getString(c.getColumnIndex("name"));

            tvUserName.setText(userName);
            listingName = listing.getTitle();
            tvListingName.setText(listingName);
            roomId = userName+"_"+MainActivity.userId+"_"+ listingName;
            roomId2 = MainActivity.userId+"_"+userName+ "_"+listingName;


        } else if (chatRoom != null)  {
            Log.i("info","coming from chat overview");
            roomId = chatRoom.getRoomId();
            loginDM = new UserDataManager(getActivity());
            Cursor c = loginDM.getUserById(chatRoom.getUserId());
            if (c.moveToNext())  userName = c.getString(c.getColumnIndex("name"));

            ChatRoomDataManager chatRoomDM = new ChatRoomDataManager(getActivity());
            c = chatRoomDM.getChat(roomId); // cehck if it has meeting exists
            if (c.moveToNext()) {
                chatRoom.setMeeting( c.getString(c.getColumnIndex("meeting")) );
            }

            listingName = chatRoom.getListing();

            if (!listingName.equals("default") ) {
                tvListingName.setText(listingName);
                tvUserName.setText(userName);
            } else {
                tvUserName.setText("");
                tvListingName.setText(userName);
            }


        } else if (meeting != null) {
            Log.i("info","coming from set meeting");
            ChatRoomDataManager chatRoomDM = new ChatRoomDataManager(getActivity());
            chatRoom = new ChatRoom();

            Cursor c = chatRoomDM.getChat(roomId); // roomId is passed with meeting
            // if this chat exists, get chat data
            if (c.moveToNext()) {
                chatRoom.setChatRoom(
                        c.getString(c.getColumnIndex("agentId")),
                        c.getString(c.getColumnIndex("userId")),
                        c.getString(c.getColumnIndex("listing"))
                );

                listingName = chatRoom.getListing();
                if (MainActivity.userId.equals(chatRoom.getUserId())) {
                    agentDM = new AgentDataManager(getActivity());
                    c = agentDM.searchRecord(chatRoom.getAgentId());
                } else {
                    loginDM = new UserDataManager(getActivity());
                    c = loginDM.getUserById(chatRoom.getUserId());
                }
                if (c.moveToNext())  userName = c.getString(c.getColumnIndex("name"));

            } else {
                // means this is either new listing or agent
                // userName and listingName would have be initiated
                chatRoom.setChatRoom( userName, MainActivity.userId, listingName );
                chatRoomDM.insert(chatRoom);
            }
            // set the chat meeting in db
            chatRoomDM.setTableRowMeeting(roomId,meeting);
            chatRoom.setMeeting(meeting);
            c = chatRoomDM.getChat(roomId); // cehck if it has meeting corrrect
            if (c.moveToNext()) {
                Log.i("info", "checking "+roomId+" has meeting "+c.getString(c.getColumnIndex("meeting")) );
            }

            if (!listingName.equals("default") ) {
                tvListingName.setText(listingName);
                tvUserName.setText(userName);
            } else {
                tvUserName.setText("");
                tvListingName.setText(userName);
            }

        }

        // meeting display
        if (chatRoom.getMeeting() == null || chatRoom.getMeeting().equals("null") ) {
            root.findViewById(R.id.tvMeetingTitle).setVisibility( View.GONE );
            root.findViewById(R.id.tvMeetingTime).setVisibility( View.GONE );
        } else {
            root.findViewById(R.id.tvMeetingTitle).setVisibility( View.VISIBLE );
            root.findViewById(R.id.tvMeetingTime).setVisibility( View.VISIBLE );
            ((TextView) root.findViewById(R.id.tvMeetingTime)).setText(chatRoom.getMeeting());
        }


        initRecyclerView();
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // do meeting sequence, just show confirmed view in the top of the chat

        view.findViewById(R.id.meetingBtn).setOnClickListener(v-> {

            OverlaySetMeetingFragment overlayFragment = new OverlaySetMeetingFragment(roomId);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.overlayFragment, overlayFragment).commit();


        });
        view.findViewById(R.id.setSellerBtn).setOnClickListener(v-> {
            ChatRoomDataManager chatRoomDM = new ChatRoomDataManager(getActivity());
            chatRoomDM.setTableRowIsSeller(roomId);

            OverlaySetSellerFragment overlayFragment = new OverlaySetSellerFragment();
//                    confirmLogoutFragment.setArguments(getActivity().getIntent().getExtras());
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.overlayFragment, overlayFragment).commit();

        });

        view.findViewById(R.id.sendBtn).setOnClickListener(v-> {

            int msgIndex = adapter.getItemCount();
            EditText etChatBox = view.findViewById(R.id.etChatBox);

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            if (etChatBox.getText().toString() != ""){
                MyMsg newMsg = new MyMsg();
                newMsg.setMsg(roomId,roomId+"_"+msgIndex, MainActivity.userId, dateTime.format(formatter), etChatBox.getText().toString());

                msgDM = new MyMsgDataManager(getActivity());
                Cursor c = msgDM.getMsg(newMsg.getMsgId());
                if (c == null || c.getCount() == 0) msgDM.insert(newMsg);

                ChatRoomDataManager chatRoomDM = new ChatRoomDataManager(getActivity());
                ChatRoom chat = new ChatRoom();
                chat.setChatRoom(userName, MainActivity.userId, listingName);
                c = chatRoomDM.getChat(roomId);
                if (c == null || c.getCount() == 0) chatRoomDM.insert(chat);

                etChatBox.setText("");
                hideSoftKeyboard(getActivity());
                mMsgList.add(newMsg);
                adapter.notifyDataSetChanged();
                mMsgRecyclerView.scrollToPosition(mMsgList.size() - 1);
            }
        });

    }

    private void initRecyclerView() {
        mMsgRecyclerView = (RecyclerView) root.findViewById(R.id.msg_recycler_view);
        mMsgRecyclerView.setHasFixedSize(true);
        //setting animation
        mMsgRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //binding layout with recycler view
        mMsgRecyclerView.setLayoutManager(layoutManager);
        //assigning adapter to RecyclerView
        setmMsgList();
        adapter = new MyMsgAdapter(getContext(),mMsgList);
        mMsgRecyclerView.setAdapter(adapter);
        mMsgRecyclerView.scrollToPosition(mMsgList.size() - 1);

    }
    public void setmMsgList(){
        mMsgList = new ArrayList<>();
        msgDM = new MyMsgDataManager(getActivity());
        Cursor c = msgDM.getMsgByRoomId(roomId); // can be null

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            MyMsg msg = new MyMsg();
            msg.setMsg( c.getString(c.getColumnIndex("roomId")),
                        c.getString(c.getColumnIndex("msgId")),
                        c.getString(c.getColumnIndex("user")),
                        c.getString(c.getColumnIndex("timestamp")),
                        c.getString(c.getColumnIndex("msg"))    );
            mMsgList.add(msg);
        }
        if (roomId2 != null){
            c = msgDM.getMsgByRoomId(roomId2); // can be null
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                MyMsg msg = new MyMsg();
                msg.setMsg(c.getString(c.getColumnIndex("roomId")),
                        c.getString(c.getColumnIndex("msgId")),
                        c.getString(c.getColumnIndex("user")),
                        c.getString(c.getColumnIndex("timestamp")),
                        c.getString(c.getColumnIndex("msg")));
                mMsgList.add(msg);
            }

        }
    }
    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null && inputManager != null) {
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                inputManager.hideSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}