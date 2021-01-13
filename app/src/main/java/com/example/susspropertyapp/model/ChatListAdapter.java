package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter < ChatListAdapter.ChatRoomViewHolder > {
    List< ChatRoom > mChatRoomList;
    private UserDataManager loginDM;
    private MyMsgDataManager msgDM;
    Context context;

    public ChatListAdapter(Context c, List <ChatRoom> agentList) {
        this.mChatRoomList = agentList;
        this.context =c;
    }
    @Override
    public ChatRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_chat, parent, false);

        return new ChatRoomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ChatRoomViewHolder holder, int position) {
        ChatRoom chat = mChatRoomList.get(position);

        loginDM = new UserDataManager(context);
        Cursor c = loginDM.getUserById(chat.getUserId());
        String userName = "";
        if (c.moveToNext()) {
            userName = c.getString(c.getColumnIndex("name"));
        }

        if (!chat.getListing().equals("default")) {
            holder.tvListing.setText(chat.getListing());
            holder.tvUser.setText(userName);
        } else {
            holder.tvUser.setText("");
            holder.tvListing.setText(userName);
        }


        msgDM = new MyMsgDataManager(context);
        c = msgDM.getMsgByRoomId(chat.getRoomId());
        String lastMsg = "";
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            lastMsg = c.getString(c.getColumnIndex("msg"));
        }
        holder.tvMsgHint.setText(lastMsg);

        holder.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("chatroom", chat) ;
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_chats_to_chatDetailFragment,bundle);

        });
    }
    @Override
    public int getItemCount() {
        return mChatRoomList.size();
    }
    public class ChatRoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvUser, tvListing, tvMsgHint;
        CardView cardView;

        public ChatRoomViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.chatCard);
            this.tvUser = itemView.findViewById(R.id.tvUser);
            this.tvListing = itemView.findViewById(R.id.tvListing);
            this.tvMsgHint = itemView.findViewById(R.id.tvMsgHint);
        }

        @Override
        public void onClick(View view) {}

    }

} 