package com.example.susspropertyapp.help.chats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.R;

public class ChatsHelpAdapter extends FragmentStateAdapter {
    public ChatsHelpAdapter(Fragment fragment) {
        super(fragment);
    }

    int[] img = { R.drawable.menu3,
            R.drawable.chat1,
            R.drawable.chat2,
            R.drawable.chat3,
            R.drawable.chat5,
            R.drawable.chat6,
            R.drawable.chat4,};

    String[] desc = { "Welcome to the Chat Menu!",
            "As always, help is ready at every page here",
            "You can sort through the different types Chats from these Tabs here",
            "Click the chat overview to start chatting",
            "Propose a meeting anytime you want",
            "You can set your Client as a Seller too",
            "Happy Messaging!",};

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ChatsHelpInnerFragment();
        Bundle args = new Bundle();

        args.putInt(ChatsHelpInnerFragment.ARG_OBJECT1, img[position]);
        args.putString(ChatsHelpInnerFragment.ARG_OBJECT2, desc[position]);
        if (position == img.length-1){ args.putString(ChatsHelpInnerFragment.ARG_OBJECT3, "yes");}
        else { args.putString(ChatsHelpInnerFragment.ARG_OBJECT3, "no");}
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return img.length;
    }
}