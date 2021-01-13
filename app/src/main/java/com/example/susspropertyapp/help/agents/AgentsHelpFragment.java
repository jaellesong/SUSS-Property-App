package com.example.susspropertyapp.help.agents;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.susspropertyapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class AgentsHelpFragment extends Fragment {

    public AgentsHelpFragment() {}
    TabLayout tabLayout;
    ViewPager2 viewPager;
    MediaPlayer mp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agents_help, container, false);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        view.findViewById(R.id.closeBtn).setOnClickListener(v -> {
            mp.start();
            requireActivity().onBackPressed();
        });
        viewPager = view.findViewById(R.id.viewPager2);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager.setAdapter( new AgentsHelpAdapter(this));

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
//                    tab.setText(titles[position])
                }
        ).attach();
    }
}