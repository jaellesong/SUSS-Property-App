package com.example.susspropertyapp.help.loan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.susspropertyapp.R;

public class LoanHelpAdapter extends FragmentStateAdapter {
    public LoanHelpAdapter(Fragment fragment) {
        super(fragment);
    }

    int[] img = { R.drawable.menu5,
            R.drawable.loan1,
            R.drawable.loan8,
            R.drawable.loan3,
            R.drawable.loan4,
            R.drawable.loan5,
            R.drawable.loan6,
            R.drawable.loan2,
            R.drawable.loan7,};

    String[] desc = { "Welcome to the Loan Calculator!",
            "As always, help is ready at every top right corner of the page here",
            "Estimate your Mortgage Amount like a Pro",
            "Enter your desired Property Value here",
            "Enter the expected Interest Rate (%) here or adjust using the slider",
            "Enter the expected Loan Tenure (Yrs) here or adjust using the slider",
            "Enter the expected Loan (%) here or adjust using the slider",
            "Entering another calculation? Just click reset here!",
            "Calculating Loans has never been so Easy!"};

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new LoanHelpInnerFragment();
        Bundle args = new Bundle();

        args.putInt(LoanHelpInnerFragment.ARG_OBJECT1, img[position]);
        args.putString(LoanHelpInnerFragment.ARG_OBJECT2, desc[position]);
        if (position == img.length-1){ args.putString(LoanHelpInnerFragment.ARG_OBJECT3, "yes");}
        else { args.putString(LoanHelpInnerFragment.ARG_OBJECT3, "no");}
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return img.length;
    }
}