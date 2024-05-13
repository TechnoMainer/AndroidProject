package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class AchiveFrag extends Fragment {
    public AchiveFrag() {
        super(R.layout.achivefragment);
    }

    String[] from = {"name", "desc", "rew"};
    int[] to = {R.id.achname, R.id.achdesc, R.id.achrew};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView achives = view.findViewById(R.id.listview);
    }
}
