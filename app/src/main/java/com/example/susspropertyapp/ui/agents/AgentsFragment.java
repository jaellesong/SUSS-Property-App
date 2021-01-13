package com.example.susspropertyapp.ui.agents;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.AgentAdapter;
import com.example.susspropertyapp.model.AgentDataManager;

import java.util.ArrayList;
import java.util.List;

public class AgentsFragment extends Fragment {

    Toolbar topAppBar;
    MediaPlayer mp;
    View root;

    RecyclerView mAgentRecyclerView;
    List<Agent> mAgentList;
    private AgentDataManager agentDM;

    public AgentsFragment() {}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_agents, container, false);
        topAppBar = root.findViewById(R.id.topAppBar);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == R.id.help) {
                NavHostFragment.findNavController(AgentsFragment.this)
                        .navigate(R.id.action_navigation_agents_to_navigation_agents_help);
            }
            return true;
        });

        agentDM = new AgentDataManager(getActivity());
        initRecyclerView();
        return root;
    }

    private void initRecyclerView() {
        mAgentRecyclerView = (RecyclerView) root.findViewById(R.id.agent_recycler_view);
        mAgentRecyclerView.setHasFixedSize(true);
        //setting animation
        mAgentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //binding layout with recycler view
        mAgentRecyclerView.setLayoutManager(layoutManager);
        //assigning adapter to RecyclerView
        RecyclerView.Adapter adapter = new AgentAdapter(setmAgentList());
        mAgentRecyclerView.setAdapter(adapter);
        TextView propertyCount = root.findViewById(R.id.tvAgentsAvailable);
        propertyCount.setText(adapter.getItemCount()+ " LISTED AGENTS");

    }

    public List<Agent> setmAgentList(){
        mAgentList = new ArrayList<>();
        Cursor c = agentDM.selectAll();

        while (c.moveToNext()) {
            Agent agent = new Agent();
            agent.setName( c.getString(c.getColumnIndex("name")) );
            agent.setId( c.getString(c.getColumnIndex("license")) );
            agent.setRating( c.getString(c.getColumnIndex("rating")) );
            agent.setBio( c.getString(c.getColumnIndex("bio")) );
            mAgentList.add(agent);
        }

        return mAgentList;
    }

}