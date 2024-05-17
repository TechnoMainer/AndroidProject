package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;
import java.util.zip.Inflater;

public class AchiveFrag extends Fragment {
    public AchiveFrag() {
        super(R.layout.achivefragment);
    }

    String[] from = {"name", "desc", "rew"};
    int[] to = {R.id.achname, R.id.achdesc, R.id.achrew};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

        View view = inflater.inflate(R.layout.achivefragment, container, false);
        ListView achives = view.findViewById(R.id.listview);
        return view;
    }
}
