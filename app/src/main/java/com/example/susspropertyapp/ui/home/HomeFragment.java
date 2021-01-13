package com.example.susspropertyapp.ui.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.SplashActivity;
import com.example.susspropertyapp.help.home.HomeHelpFragment;
import com.example.susspropertyapp.login.ui.LoginActivity;
import com.example.susspropertyapp.login.ui.MainLoginActivity;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;
    Toolbar topAppBar;
    MediaPlayer mp;
    View root;
    public HomeFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, null);
        topAppBar = root.findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            switch (item.getItemId()) {
                case R.id.logout:
                    OverlayLogoutFragment intent = new OverlayLogoutFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.overlayFragment, intent).commit();
                    break;
                case R.id.help:
                    NavHostFragment.findNavController(HomeFragment.this)
                            .navigate(R.id.action_navigation_home_to_navigation_home_help);
                    break;
                default:
                    break;
            }
            return true;
        });

//        view.findViewById(R.id.searchDistrictBtn).setOnClickListener(view1 -> NavHostFragment.findNavController(HomeFragment.this)
//                .navigate(R.id.action_navigation_home_to_navigation_home_search_list));
//        view.findViewById(R.id.searchEstateBtn).setOnClickListener(view1 -> NavHostFragment.findNavController(HomeFragment.this)
//                .navigate(R.id.action_navigation_home_to_navigation_home_search_list));
//        view.findViewById(R.id.searchMrtBtn).setOnClickListener(view1 -> NavHostFragment.findNavController(HomeFragment.this)
//                .navigate(R.id.action_navigation_home_to_navigation_home_search_list));
//
        view.findViewById(R.id.searchBtn).setOnClickListener(view1 -> {
            NavHostFragment.findNavController(HomeFragment.this)
                    .navigate(R.id.action_navigation_home_to_navigation_home_results);
        });

        view.findViewById(R.id.pickLocationBtn).setOnClickListener(view1 ->{
            Intent intent = new Intent(getActivity(), com.example.susspropertyapp.ui.home.MapActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity());
            startActivity(intent, options.toBundle());
        });
    }


}