package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;
import java.util.zip.Inflater;

public class AchiveFrag extends Fragment {
    public AchiveFrag() {
        super(R.layout.achivefragment);
    }

    String[] from = {"name", "desc", "rew"};
    int[] to = {R.id.achname, R.id.achdesc, R.id.achrew};

    ImageView cross;
    FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.achivefragment, container, false);

        Fragment frag = this;



        cross = view.findViewById(R.id.imageView);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(frag).commit();
                Log.d("MYLOG", "closed");
            }
        });

        return view;
    }
}
