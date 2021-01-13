package com.example.susspropertyapp.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.Listing;

public class PropertyDetailFragment extends Fragment {

    Toolbar topAppBar;
    MediaPlayer mp;
    Listing listing = null;

    TextView title;
    TextView price;
    TextView overview;

    TextView address;
    TextView type;
    TextView year;
    TextView area;
    TextView bathrooms;
    TextView bedrooms;
    TextView tenure;
    TextView status;
    TextView keywords;
    TextView description;


    public PropertyDetailFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_property_detail, null);
        topAppBar = root.findViewById(R.id.topAppBar);
        return root;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mp = MediaPlayer.create(getContext(), R.raw.button);

        title = view.findViewById(R.id.tvTitle);
        price = view.findViewById(R.id.tvPrice);
        overview = view.findViewById(R.id.tvDetails);
        address = view.findViewById(R.id.editAddress);
        type = view.findViewById(R.id.editType);
        year = view.findViewById(R.id.editYear);
        area = view.findViewById(R.id.editArea);
        bathrooms = view.findViewById(R.id.editBaths);
        bedrooms = view.findViewById(R.id.editBeds);
        tenure = view.findViewById(R.id.editTenure);
        status = view.findViewById(R.id.editStatus);
        keywords = view.findViewById(R.id.editKeywords);
        description = view.findViewById(R.id.editDescription);


        Intent intent = getActivity().getIntent();
        Bundle bundle = this.getArguments();
        if (!bundle.isEmpty()) {
            listing = (Listing) bundle.getSerializable("listing");
        }
        // from new listing activity
        else if (intent.getExtras() != null ) {
            listing = (Listing) intent.getExtras().getSerializable("listing");
        }

        if (listing != null){

            int psf = Integer.parseInt(listing.getPrice()) /
                    Integer.parseInt(listing.getArea());

            String overviewText = listing.getBedrooms() + " Beds • " +
                    listing.getBathrooms() + " Baths • " +
                    listing.getArea() + " sqft • $" +
                    psf + " psf";

            title.setText(listing.getTitle());
            price.setText("S$ "+listing.getPrice());
            overview.setText(overviewText);
            address.setText(listing.getAddress());
            type.setText(listing.getType());
            year.setText(listing.getYear());
            area.setText(listing.getArea());
            bathrooms.setText(listing.getBathrooms());
            bedrooms.setText(listing.getBedrooms());
            tenure.setText(listing.getTenure());
            status.setText(listing.getStatus());
            keywords.setText(listing.getKeywords());
            description.setText(listing.getDescription());


            view.findViewById(R.id.enquireBtn).setOnClickListener((v -> {
                Bundle putBundle = new Bundle();
                putBundle.putSerializable("listing", listing);
                NavHostFragment.findNavController(PropertyDetailFragment.this)
                        .navigate(R.id.action_navigation_property_detail_to_chatDetailFragment,putBundle);
                }
            ));
        }


        topAppBar.setNavigationOnClickListener(view1 -> requireActivity().onBackPressed() );
        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            if (item.getItemId() == R.id.loan) {
                Bundle bundle2 = new Bundle();
                TextView price = view.findViewById(R.id.tvPrice);
                bundle2.putString("amount", (String) price.getText());
                NavHostFragment.findNavController(PropertyDetailFragment.this)
                        .navigate(R.id.action_navigation_property_detail_to_navigation_loan_property, bundle2);
            }
            return true;
        });

    }
}