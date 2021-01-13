package com.example.susspropertyapp.ui.account;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AccountViewPagerActivity extends FragmentStateAdapter {
    public AccountViewPagerActivity(Fragment fragment) {
        super(fragment);
    }
    static String agentId;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String id = agentId;
        switch (position){
            case 0:
                return new AccountListingsFragment(id);
            case 1:
                return new AccountReviewsFragment();
            default:
                return new AccountListingsFragment(id);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}