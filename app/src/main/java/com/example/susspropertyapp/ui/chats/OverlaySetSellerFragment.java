package com.example.susspropertyapp.ui.chats;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.ui.account.NewListingActivity;


public class OverlaySetSellerFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmLogoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OverlaySetSellerFragment newInstance(String param1, String param2) {
        OverlaySetSellerFragment fragment = new OverlaySetSellerFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overlay_set_seller, container, false);

        root.findViewById(R.id.shadedOverlay).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });
        root.findViewById(R.id.closeBtn).setOnClickListener(v-> {
//            requireActivity().onBackPressed();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });
        root.findViewById(R.id.newPropertyBtn).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();

            Intent intent = new Intent(getActivity(), NewListingActivity.class);
            intent.putExtra("agentId", MainActivity.userId);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity());
            startActivity(intent, options.toBundle());

            // open new frag for new property
        });

        root.findViewById(R.id.existingPropertyBtn).setOnClickListener(v-> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
            // ask to select from pop out list

        });


        return root;

    }


}