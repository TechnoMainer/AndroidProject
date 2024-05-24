package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


public class Automation extends Fragment {


    public int ascendmulty;

    ArrayList<Map<String, Object>> Factories = new ArrayList<>();
    static CustomAdapter adapter;

    ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_automation, container, false);
        //fm = getSupportFragmentManager();
        listView = view.findViewById(R.id.listview);

        MainActivity.mon.setText(String.valueOf(MainActivity.money.toBigInteger()));
        Factory fac1 = new Factory(BigDecimal.valueOf(10), 10000, 1.0);
        fac1.upglv = 1;
        fac1.upgcost = BigDecimal.valueOf(30);
        Factory fac2 = new Factory(BigDecimal.valueOf(100), 10000, 1.0);
        Factory fac3 = new Factory(BigDecimal.valueOf(1000), 10000, 1.0);
        Factory fac4 = new Factory(BigDecimal.valueOf(10000), 10000, 1.0);
        Factory fac5 = new Factory(BigDecimal.valueOf(100000), 10000, 1.0);
        Factory fac6 = new Factory(BigDecimal.valueOf(1000000), 10000, 1.0);
        Factory fac7 = new Factory(BigDecimal.valueOf(10000000), 10000, 1.0);
        Factory fac8 = new Factory(BigDecimal.valueOf(100000000), 10000, 1.0);

        Factory[] facts = {fac1, fac2, fac3, fac4, fac5, fac6, fac7, fac8};
        fac1.gen.start();

        adapter = new CustomAdapter(facts, view.getContext());
        listView.setAdapter(adapter);

        return view;
    }
}

