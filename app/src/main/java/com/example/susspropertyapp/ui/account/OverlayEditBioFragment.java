package com.example.susspropertyapp.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.AgentDataManager;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingDataManager;
import com.example.susspropertyapp.ui.chats.MyDatePickerFragment;
import com.example.susspropertyapp.ui.chats.MyTimePickerFragment;

import java.util.List;


public class OverlayEditBioFragment extends Fragment {

    String agentId;
    private AgentDataManager agentDM;

    public OverlayEditBioFragment(String id) {
        agentId = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agentDM = new AgentDataManager(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overlay_edit_bio, container, false);

        root.findViewById(R.id.shadedOverlay).setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });
        root.findViewById(R.id.closeBtn).setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
        });

        EditText editBio = root.findViewById(R.id.editBio);
        editBio.setText(AccountFragment.bioText.getText());
        root.findViewById(R.id.confirmBtn).setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commit();
            AccountFragment.bioText.setText(editBio.getText().toString());
            agentDM.updateAgentBio(agentId, editBio.getText().toString());
        });

        return root;

    }
}

