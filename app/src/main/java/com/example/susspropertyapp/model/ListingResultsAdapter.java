package com.example.susspropertyapp.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.R;

import java.util.List;

public class ListingResultsAdapter extends RecyclerView.Adapter < ListingResultsAdapter.ListingViewHolder > {
    List< Listing > mListingList;
    public ListingResultsAdapter(List <Listing> listingList) {
        this.mListingList = listingList;
    }
    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_listing, parent, false);

        return new ListingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        Listing listing = mListingList.get(position);
        holder.tvTitle.setText(listing.getTitle());
        holder.tvPrice.setText("S$ "+listing.getPrice());
        holder.cardView.setOnClickListener(v -> {

            Listing listing1 = mListingList.get(position);

            Bundle bundle = new Bundle();
            bundle.putSerializable("listing", listing1);
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_home_results_to_navigation_property_detail,bundle);

        });

        int psf = Integer.parseInt(listing.getPrice()) /
                Integer.parseInt(listing.getArea());
        String overviewText = listing.getBedrooms() + " Beds • " +
                listing.getBathrooms() + " Baths • " +
                listing.getArea() + " sqft • $" +
                psf + " psf";

        holder.tvDetails.setText(overviewText);
    }
    @Override
    public int getItemCount() {
        return mListingList.size();
    }
    public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle, tvPrice, tvDetails;
        CardView cardView;

        public ListingViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.tvDetails = itemView.findViewById(R.id.tvDetails);
        }

        @Override
        public void onClick(View view) {
            Listing listing = mListingList.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putSerializable("listing", listing);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.action_navigation_home_results_to_navigation_property_detail,bundle);
        }

    }

} 