package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        ImageView closebutton = view.findViewById(R.id.close);
        ListView achives = view.findViewById(R.id.listview);

        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });
    }
}
