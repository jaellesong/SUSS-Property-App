package com.example.susspropertyapp.model;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.R;

import java.util.List;

public class AgentAdapter extends RecyclerView.Adapter < AgentAdapter.AgentViewHolder > {
    List< Agent > mAgentList;
    public AgentAdapter(List <Agent> agentList) {
        this.mAgentList = agentList;
    }
    @Override
    public AgentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_agent, parent, false);

        return new AgentViewHolder(view);
    }
    @Override
    public void onBindViewHolder(AgentViewHolder holder, int position) {
        Agent agent = mAgentList.get(position);
        holder.tvName.setText(agent.getName());
        holder.tvRating.setText(agent.getRating());
        holder.tvId.setText(agent.getId());
        holder.tvRating.setText(agent.getRating());
        holder.ratingBar.setRating(Float.parseFloat(agent.getRating()));
        holder.cardView.setOnClickListener(v -> {
            Bundle putBundle = new Bundle();
            putBundle.putSerializable("agent", agent);
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_agents_to_navigation_agents_detail,putBundle);

        });

        holder.enquireBtn.setOnClickListener(v -> {
            Bundle putBundle = new Bundle();
            putBundle.putSerializable("agent", agent);
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_agents_to_chatDetailFragment,putBundle);

        });
    }
    @Override
    public int getItemCount() {
        return mAgentList.size();
    }
    public class AgentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvRating, tvId;
        CardView cardView;
        RatingBar ratingBar;
        Button enquireBtn;

        public AgentViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardAgent);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvRating = itemView.findViewById(R.id.tvRating);
            this.tvId = itemView.findViewById(R.id.tvAgentId);
            this.ratingBar = itemView.findViewById(R.id.ratingBar);
            this.enquireBtn = itemView.findViewById(R.id.enquireBtn);
        }

        @Override
        public void onClick(View view) {
//            Agent agent = mAgentList.get(getAdapterPosition());
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("agent", agent);
//            AppCompatActivity activity = (AppCompatActivity) view.getContext();
//            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
//            navController.navigate(R.id.action_navigation_agents_to_navigation_account,bundle);
        }

    }

} 