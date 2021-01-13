package com.example.susspropertyapp.ui.agents;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.ui.agents.AgentViewPagerActivity;
import com.example.susspropertyapp.ui.account.OverlayEditBioFragment;
import com.example.susspropertyapp.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AgentsDetailFragment extends Fragment {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    Toolbar topAppBar;
    MediaPlayer mp;
    private String[] titles = new String[]{"Listings", "Reviews"};
    Agent agent = null;

    public AgentsDetailFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_agents_detail, container, false);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        topAppBar = root.findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(view1 -> requireActivity().onBackPressed() );

        TextView name = root.findViewById(R.id.tvName);
        TextView id = root.findViewById(R.id.tvLicense);
        TextView bioText = root.findViewById(R.id.tvBio);
        TextView rating = root.findViewById(R.id.tvRating);
        RatingBar ratingBar = root.findViewById(R.id.ratingBar);
//        ratingBar.setRating(Float.parseFloat(rating.getText().toString()));

        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.viewPager);

        Bundle bundle = this.getArguments();

        if (!bundle.isEmpty()) {
            agent = (Agent) bundle.getSerializable("agent");
        }
        if (agent != null) {
            name.setText(agent.getName());
            id.setText(agent.getId());
            rating.setText(agent.getRating());
            ratingBar.setRating(Float.parseFloat(agent.getRating()));
            bioText.setText(agent.getBio());

            AgentViewPagerActivity pagerAdapter = new AgentViewPagerActivity(this);
            AgentViewPagerActivity.agentId = id.getText().toString();
            viewPager.setAdapter(pagerAdapter);
        }

        root.findViewById(R.id.enquireBtn).setOnClickListener(v -> {

            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("agent",agent);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_navigation_agents_detail_to_chatDetailFragment, bundle1);

        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }

}
