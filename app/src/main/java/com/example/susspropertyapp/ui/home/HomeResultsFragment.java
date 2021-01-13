package com.example.susspropertyapp.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingResultsAdapter;
import com.example.susspropertyapp.model.ListingDataManager;

import java.util.ArrayList;
import java.util.List;

public class HomeResultsFragment extends Fragment {

    Toolbar topAppBar;
    MediaPlayer mp;
    View root;
    RecyclerView mListingRecyclerView;
    List<Listing> mListingList;
    private ListingDataManager listingDM;
    public HomeResultsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home_results, null);
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

        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == R.id.filter) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
            return true;
        });

        initRecyclerView();
    }
    

    private void initRecyclerView() {
        mListingRecyclerView = (RecyclerView) root.findViewById(R.id.listing_recycler_view);
        mListingRecyclerView.setHasFixedSize(true);
        //setting animation
        mListingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //binding layout with recycler view
        mListingRecyclerView.setLayoutManager(layoutManager);
        //assigning adapter to RecyclerView
        RecyclerView.Adapter adapter = new ListingResultsAdapter(setmListingList());
        mListingRecyclerView.setAdapter(adapter);
        TextView listingCount = root.findViewById(R.id.tvPropertySale);
        listingCount.setText(adapter.getItemCount()+ " PROPERTIES FOR SALE");

    }

    public List<Listing> setmListingList(){

        listingDM = new ListingDataManager(getActivity());
        // get data to return as array
        mListingList = new ArrayList<>();
        Cursor c = listingDM.selectAll();

        while (c.moveToNext()) {
            Listing listing = new Listing();
            listing.setTitle( c.getString(c.getColumnIndex("title")) );
            listing.setAddress( c.getString(c.getColumnIndex("address")) );
            listing.setLatitude( c.getString(c.getColumnIndex("latitude")) );
            listing.setLongitude( c.getString(c.getColumnIndex("longitude")) );
            listing.setDescription( c.getString(c.getColumnIndex("description")) );
            listing.setType( c.getString(c.getColumnIndex("type")) );
            listing.setBathrooms( c.getString(c.getColumnIndex("bathrooms")) );
            listing.setBedrooms( c.getString(c.getColumnIndex("bedrooms")) );
            listing.setPrice( c.getString(c.getColumnIndex("price")) );
            listing.setArea( c.getString(c.getColumnIndex("area")) );
            listing.setYear( c.getString(c.getColumnIndex("year")) );
            listing.setTenure( c.getString(c.getColumnIndex("tenure")) );
            listing.setStatus( c.getString(c.getColumnIndex("status")) );
            listing.setKeywords( c.getString(c.getColumnIndex("keywords")) );
            listing.setAgentId( c.getString(c.getColumnIndex("agent")) );
            mListingList.add(listing);
        }
        return mListingList;
    }
}