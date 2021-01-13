package com.example.susspropertyapp.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.susspropertyapp.R;

public class HomeSearchListFragment extends Fragment {

    Toolbar topAppBar;
    MediaPlayer mp;
    public HomeSearchListFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_search_list, null);
        topAppBar = root.findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topAppBar.setNavigationOnClickListener(view1 -> {
            mp.start();
            requireActivity().onBackPressed();
        });

        view.findViewById(R.id.continueBtn).setOnClickListener(view1 -> NavHostFragment.findNavController(HomeSearchListFragment.this)
                .navigate(R.id.action_navigation_home_search_list_to_navigation_home_results));
    }

}