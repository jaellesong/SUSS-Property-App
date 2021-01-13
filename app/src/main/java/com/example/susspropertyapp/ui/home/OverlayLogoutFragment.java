package com.example.susspropertyapp.ui.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.SplashActivity;
import com.example.susspropertyapp.login.ui.MainLoginActivity;


public class OverlayLogoutFragment extends Fragment {

    public OverlayLogoutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overlay_logout, container, false);

        root.findViewById(R.id.shadedOverlay).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });
        root.findViewById(R.id.noBtn).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });
        root.findViewById(R.id.yesBtn).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
            Intent intent = new Intent(getActivity(), MainLoginActivity.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity());
            startActivity(intent, options.toBundle());
        });

        return root;

    }
}