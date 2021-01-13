package com.example.susspropertyapp.ui.account;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.susspropertyapp.MainActivity;
import com.example.susspropertyapp.R;
import com.example.susspropertyapp.model.AgentDataManager;
import com.example.susspropertyapp.ui.agents.AgentViewPagerActivity;
import com.example.susspropertyapp.ui.chats.OverlaySetSellerFragment;
import com.example.susspropertyapp.ui.loan.LoanFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Objects;

public class AccountFragment extends Fragment {

    ViewPager2 viewPager;
    TabLayout tabLayout;
    Toolbar topAppBar;
    MediaPlayer mp;
    public static TextView bioText;
    private AgentDataManager agentDM;
    String userId;
    private String[] titles = new String[]{"Listings", "Reviews"};

    public AccountFragment() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);
        mp = MediaPlayer.create(getContext(), R.raw.button);
        topAppBar = root.findViewById(R.id.topAppBar);


        userId = MainActivity.userId;
        Log.i("info","account user id is "+userId);
        if (userId == null){
            userId = "R059432Z";
        }

        TextView name = root.findViewById(R.id.tvName);
        TextView id =root.findViewById(R.id.tvLicense);
        TextView rating = root.findViewById(R.id.tvRating);
        RatingBar ratingBar = root.findViewById(R.id.ratingBar);
        bioText = root.findViewById(R.id.tvBio);

        tabLayout = root.findViewById(R.id.tabLayout);
        viewPager = root.findViewById(R.id.viewPager);
        AccountViewPagerActivity pagerAdapter = new AccountViewPagerActivity(this);
        AccountViewPagerActivity.agentId = userId;
        viewPager.setAdapter(pagerAdapter);

        agentDM = new AgentDataManager(getActivity());
        Cursor c = null;
        c =  agentDM.searchRecord(userId);
        if ( c.getCount() == 1 && c != null){
            while (c.moveToNext()) {
                name.setText( c.getString(c.getColumnIndex("name")) );
                id.setText( c.getString(c.getColumnIndex("license")) );
                rating.setText( c.getString(c.getColumnIndex("rating")) );
                ratingBar.setRating(Float.parseFloat(c.getString(c.getColumnIndex("rating"))));
                bioText.setText( c.getString(c.getColumnIndex("bio")) );
            }
        }


        topAppBar.setNavigationOnClickListener(view1 -> requireActivity().onBackPressed() );
        topAppBar.setOnMenuItemClickListener(item -> {
            mp.start();
            switch (item.getItemId()) {
                case R.id.edit:
                    OverlayEditBioFragment overlayFragment =
                            new OverlayEditBioFragment( userId );
//                    confirmLogoutFragment.setArguments(getActivity().getIntent().getExtras());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.overlayFragment, overlayFragment).commit();

                    break;
                case R.id.help:
                    NavHostFragment.findNavController(AccountFragment.this)
                            .navigate(R.id.action_navigation_account_to_navigation_account_help);
                    break;
                default:
                    break;
            }
            return true;
        });

        root.findViewById(R.id.newListingBtn).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NewListingActivity.class);
            intent.putExtra("agentId", userId );
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity());
            startActivity(intent, options.toBundle());
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }

}
