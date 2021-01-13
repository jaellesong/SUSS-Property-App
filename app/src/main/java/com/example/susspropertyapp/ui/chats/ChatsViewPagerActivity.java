package com.example.susspropertyapp.ui.chats;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ChatsViewPagerActivity extends FragmentStateAdapter {
    public ChatsViewPagerActivity(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChatListAllFragment();
            case 1:
                return new ChatListBuyingFragment();
            case 2:
                return new ChatListSellingFragment();
            case 3:
                return new ChatListEnquiresFragment();
            default:
                return new ChatListAllFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}