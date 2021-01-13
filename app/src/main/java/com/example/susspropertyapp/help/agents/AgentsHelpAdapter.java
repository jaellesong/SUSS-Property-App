package com.example.susspropertyapp.help.agents;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.help.home.HomeHelpInnerFragment1;

public class AgentsHelpAdapter extends FragmentStateAdapter {
    public AgentsHelpAdapter(Fragment fragment) {
        super(fragment);
    }

    int[] img = { R.drawable.menu2,
            R.drawable.agent1,
            R.drawable.agent2,
            R.drawable.agent3,
            R.drawable.agent4 };

    String[] desc = { "Welcome to the Agent Searcher!",
            "As always, help is ready at every top right corner of the page here",
            "Search for our Best Agents by Names here",
            "Click to View any Agent Detail. " +
                    "You may view their rating and Agent IDs here too",
            "Can't wait? Enquire Directly Now!"};

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new AgentsHelpInnerFragment();
        Bundle args = new Bundle();

        args.putInt(AgentsHelpInnerFragment.ARG_OBJECT1, img[position]);
        args.putString(AgentsHelpInnerFragment.ARG_OBJECT2, desc[position]);
        if (position == img.length-1){ args.putString(AgentsHelpInnerFragment.ARG_OBJECT3, "yes");}
        else { args.putString(AgentsHelpInnerFragment.ARG_OBJECT3, "no");}
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return img.length;
    }
}