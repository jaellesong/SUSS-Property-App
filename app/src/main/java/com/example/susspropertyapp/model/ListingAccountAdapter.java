package com.example.susspropertyapp.model;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susspropertyapp.R;

import java.util.List;

public class ListingAccountAdapter extends RecyclerView.Adapter < ListingAccountAdapter.ListingViewHolder > {
    List< Listing > mListingList;
    ListingDataManager listDM;

    public ListingAccountAdapter(List <Listing> listingList) {
        this.mListingList = listingList;
    }
    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_listing_account, parent, false);

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
            navController.navigate(R.id.action_navigation_account_to_navigation_property_detail,bundle);

        });
        holder.optionButton.setOnClickListener(v->{

            AppCompatActivity activity = (AppCompatActivity) v.getContext();

            listDM = new ListingDataManager(activity);
            Cursor c = listDM.searchSold(listing.getTitle());
            if (c.getCount() == 0){

                PopupMenu popup = new PopupMenu( activity,v);
                popup.inflate(R.menu.property_option);
                popup.show();

                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.editProperty:
                            Toast.makeText(activity, "Editing is still a WIP"
                                    , Toast.LENGTH_LONG).show();
                            return true;
                        case R.id.markSold:
                            listing.setIsSold();
                            listDM.setTableRowSold(listing.getTitle());
                            Toast.makeText(activity, listing.getTitle()+ "is Sold! "
                                    , Toast.LENGTH_LONG).show();
                            return true;
                        default:
                            return false;
                    }
                });
            } else{
                Toast.makeText(activity, "This property is sold. You can't edit anymore!"
                        , Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return mListingList.size();
    }

    public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle, tvPrice, tvDetails;
        CardView cardView;
        ImageButton optionButton;

        public ListingViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.tvDetails = itemView.findViewById(R.id.tvDetails);
            this.optionButton = itemView.findViewById(R.id.optionBtn);
        }

        @Override
        public void onClick(View view) {
//            Listing listing = mListingList.get(getAdapterPosition());
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("listing", listing);
//            AppCompatActivity activity = (AppCompatActivity) view.getContext();
//            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
//            navController.navigate(R.id.action_navigation_home_results_to_navigation_property_detail,bundle);
        }

    }

} 