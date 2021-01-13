package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;

import java.util.List;

public class MyMsgAdapter extends RecyclerView.Adapter < MyMsgAdapter.MyMsgViewHolder > {
    List< MyMsg > mMyMsgList;
    private UserDataManager loginDM;
    Context context;
    public MyMsgAdapter(Context context, List <MyMsg> msgList) {
        this.mMyMsgList = msgList;
        this.context = context;
    }
    @Override
    public MyMsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_view, parent, false);
        return new MyMsgViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyMsgViewHolder holder, int position) {

        MyMsg msg = mMyMsgList.get(position);
        // if its my message

        if ( msg.getUser().equals(MainActivity.userId+"") ){
            holder.layout_in.setVisibility( View.GONE );
            holder.layout_out.setVisibility( View.VISIBLE );
            holder.text_message_body_out.setText( msg.getMsg() );
            holder.text_message_time_out.setText( msg.getTimestamp() );

        } else if (msg.getUser() != MainActivity.userId ){
            //if its not my message
            holder.layout_out.setVisibility( View.GONE );
            holder.layout_in.setVisibility( View.VISIBLE );
            loginDM = new UserDataManager(context);
            Cursor c = loginDM.getUserById(msg.getUser());
            String userName = "";
            if (c.moveToNext())  userName = c.getString(c.getColumnIndex("name"));

            holder.text_message_name_in.setText( userName );
            holder.text_message_body_in.setText( msg.getMsg() );
            holder.text_message_time_in.setText( msg.getTimestamp() );
        }

    }
    @Override
    public int getItemCount() {
        return mMyMsgList.size();
    }

    public class MyMsgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_message_name_in, text_message_body_in, text_message_time_in, text_message_body_out, text_message_time_out;
        ConstraintLayout layout_in, layout_out;

        public MyMsgViewHolder(View itemView) {
            super(itemView);
            this.text_message_name_in = itemView.findViewById(R.id.text_message_name_in);
            this.text_message_body_in = itemView.findViewById(R.id.text_message_body_in);
            this.text_message_time_in = itemView.findViewById(R.id.text_message_time_in);
            this.text_message_body_out = itemView.findViewById(R.id.text_message_body_out);
            this.text_message_time_out = itemView.findViewById(R.id.text_message_time_out);
            this.layout_in = itemView.findViewById(R.id.layout_in);
            this.layout_out = itemView.findViewById(R.id.layout_out);
        }

        @Override
        public void onClick(View view) {}

    }
    public void add(MyMsg msg){
        mMyMsgList.add(msg);
    }

} 