package com.example.susspropertyapp.ui.agents;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.ui.account.AccountListingsFragment;
import com.example.susspropertyapp.ui.account.AccountReviewsFragment;

public class AgentViewPagerActivity extends FragmentStateAdapter {
    public AgentViewPagerActivity(Fragment fragment) {
        super(fragment);
    }
    static String agentId;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String id = agentId;
        switch (position){
            case 0:
                return new AgentListingsFragment(id);
            case 1:
                return new AgentReviewsFragment();
            default:
                return new AgentListingsFragment(id);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}