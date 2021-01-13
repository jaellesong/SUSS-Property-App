package com.example.susspropertyapp.ui.chats;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.ChatListAdapter;
import com.example.susspropertyapp.model.ChatRoom;
import com.example.susspropertyapp.model.ChatRoomDataManager;

import java.util.ArrayList;
import java.util.List;

public class ChatListSellingFragment extends Fragment {
    View root;
    RecyclerView mChatRoomRecyclerView;
    List<ChatRoom> mChatRoomList;
    RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_chat_list, container, false);
        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mChatRoomRecyclerView = (RecyclerView) root.findViewById(R.id.chat_recycler_view);
        mChatRoomRecyclerView.setHasFixedSize(true);
        //setting animation
        mChatRoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //binding layout with recycler view
        mChatRoomRecyclerView.setLayoutManager(layoutManager);
        //assigning adapter to RecyclerView
        adapter = new ChatListAdapter(getContext(),setmChatRoomList());
        mChatRoomRecyclerView.setAdapter(adapter);

    }
    public List<ChatRoom> setmChatRoomList(){
        ChatRoomDataManager chatroomDM = new ChatRoomDataManager(getActivity());
        // get data to return as array
        mChatRoomList = new ArrayList<>();
        Cursor c = chatroomDM.getIsSeller(MainActivity.userId);

        while (c.moveToNext()) {
            ChatRoom chatroom = new ChatRoom();
            chatroom.setChatRoom(
                    c.getString(c.getColumnIndex("agentId")) ,
                    c.getString(c.getColumnIndex("userId")) ,
                    c.getString(c.getColumnIndex("listing"))
            );
            mChatRoomList.add(chatroom);
        }
        return mChatRoomList;
    }

}