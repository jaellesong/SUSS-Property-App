package com.example.susspropertyapp.help.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.susspropertyapp.R;

import org.w3c.dom.Text;

public class HomeHelpInnerFragment2 extends Fragment {
    MediaPlayer mp;
    public static final String ARG_OBJECT1 = String.valueOf(R.drawable.menu1);
    public static final String ARG_OBJECT3 = "no";
    public static final String ARG_OBJECT2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            "Vestibulum fermentum sodales dui. Donec pulvinar," +
            "ante euismod finibus finibus, nibh sem venenatis lorem," +
            "in consectetur ex mauris id ex. ";

    public HomeHelpInnerFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mp = MediaPlayer.create(getContext(), R.raw.completed);
        return inflater.inflate(R.layout.fragment_home_help2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(args.getInt(ARG_OBJECT1));

        TextView description = view.findViewById(R.id.description);
        description.setText(args.getString(ARG_OBJECT2));

        Button finish = view.findViewById(R.id.finish);
        finish.setOnClickListener(v -> {
            mp.start();
            requireActivity().onBackPressed();
        });
        Log.i("info",args.getString(ARG_OBJECT3));
        if (args.getString(ARG_OBJECT3) == "yes"){
            finish.setVisibility(View.VISIBLE);
        }else{
            finish.setVisibility(View.GONE);
        }
    }
}