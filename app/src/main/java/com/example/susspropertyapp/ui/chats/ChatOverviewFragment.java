package com.example.susspropertyapp.ui.chats;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.ui.agents.AgentsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChatOverviewFragment extends Fragment {

//    ChatsViewPagerActivity demoCollectionAdapter;
    ViewPager2 viewPager;
    TabLayout tabLayout;
    Toolbar topAppBar;
    MediaPlayer mp;
    private String[] titles = new String[]{"All Chats", "Buying", "Selling","My Enquires"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat_overview, container, false);

        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        viewPager = (ViewPager2) root.findViewById(R.id.viewPager);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        FragmentStateAdapter pagerAdapter = new ChatsViewPagerActivity(this);
        viewPager.setAdapter(pagerAdapter);

        topAppBar = root.findViewById(R.id.topAppBar);
        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == R.id.help) {
                NavHostFragment.findNavController(ChatOverviewFragment.this)
                        .navigate(R.id.action_navigation_chats_to_navigation_chats_help);
            }
            return true;
        });

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }
}