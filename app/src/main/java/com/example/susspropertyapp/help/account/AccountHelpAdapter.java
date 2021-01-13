package com.example.susspropertyapp.help.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.R;

public class AccountHelpAdapter extends FragmentStateAdapter {
    public AccountHelpAdapter(Fragment fragment) {
        super(fragment);
    }

    int[] img = { R.drawable.menu4,
            R.drawable.acc1,
            R.drawable.acc2,
            R.drawable.acc3,
            R.drawable.acc4,
            R.drawable.acc5 };

    String[] desc = { "Welcome to the Agent Account Page!",
            "As always, help is ready at every top right corner of the page here",
            "You can Edit your Agent Description here",
            "You can view your listed Properties and Reviews from these Tabs here",
            "Add a new Property listing easily from the bottom right corner here",
            "Edit your Property or Mark it as Sold here"};

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new AccountHelpInnerFragment();
        Bundle args = new Bundle();

        args.putInt(AccountHelpInnerFragment.ARG_OBJECT1, img[position]);
        args.putString(AccountHelpInnerFragment.ARG_OBJECT2, desc[position]);
        if (position == img.length-1){ args.putString(AccountHelpInnerFragment.ARG_OBJECT3, "yes");}
        else { args.putString(AccountHelpInnerFragment.ARG_OBJECT3, "no");}
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return img.length;
    }
}