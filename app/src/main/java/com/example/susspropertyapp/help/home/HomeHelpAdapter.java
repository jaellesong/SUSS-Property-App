package com.example.susspropertyapp.help.home;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.R;

public class HomeHelpAdapter extends FragmentStateAdapter {
    public HomeHelpAdapter(Fragment fragment) {
        super(fragment);
    }
    int[] img = { R.drawable.menu1,
            R.drawable.home1,
            R.drawable.home2,
            R.drawable.home3,
            R.drawable.home4,
            R.drawable.home5 };

    String[] desc = { "You can search for properties from the Home Menu here",
            "Help is ready at every top right corner of the page here",
            "Logout from your account here",
            "You can search for properties by typing keywords here",
            "Or select from the options below",
            "What are you waiting for? Let the House Hunt Begin!"};

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new HomeHelpInnerFragment1();
        } else {
            Fragment fragment = new HomeHelpInnerFragment2();
            Bundle args = new Bundle();
            args.putInt(HomeHelpInnerFragment2.ARG_OBJECT1, img[position-1]);
            args.putString(HomeHelpInnerFragment2.ARG_OBJECT2, desc[position-1]);
            if (position == img.length){ args.putString(HomeHelpInnerFragment2.ARG_OBJECT3, "yes");}
            else { args.putString(HomeHelpInnerFragment2.ARG_OBJECT3, "no");}
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    public int getItemCount() {
        return img.length+1;
    }
}