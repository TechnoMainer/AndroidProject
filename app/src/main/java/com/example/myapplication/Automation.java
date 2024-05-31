package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Automation extends Fragment {


    public static float ascendmulty = 0.4F;

    ArrayList<Map<String, Object>> Factories = new ArrayList<>();
    static CustomAdapter adapter;
    View view;

    ListView listView;

    static Factory fac1, fac2, fac3, fac4, fac5, fac6, fac7, fac8;
    Factory[] facts = {fac1, fac2, fac3, fac4, fac5, fac6, fac7, fac8};

    static long asccount;

    SharedPreferences autodata;
    String[] names = {"Proxima Centauri", "Sun", "Sirius", "Arcturus", "Betelgeuse", "Black Hole A0620-00", "Ton-618", "Phoenix A"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences.Editor editor = autodata.edit();
        for(int i = 0; i < facts.length; i++){
            editor.putInt(i+"upglv", facts[i].upglv);
            editor.putInt(i+"asclv", facts[i].ascendlv);
            editor.putBoolean(i+"bool", facts[i].genStarted);
        }
        editor.putFloat("ascmulty", ascendmulty);
        editor.putLong("asccount", asccount);
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_automation, container, false);

        listView = view.findViewById(R.id.listview);

        init();

        adapter = new CustomAdapter(facts, view.getContext());
        listView.setAdapter(adapter);

        return view;
    }

    public void init(){
        autodata = view.getContext().getSharedPreferences("AutoData", Context.MODE_PRIVATE);
        for(int i = 0; i<facts.length; i++){
            int upglv = autodata.getInt(i+"upglv", 0);
            int asclv = autodata.getInt(i+"asclv", 0);
            String name = names[i];
            boolean gen = autodata.getBoolean(i+"bool", false);
            facts[i] = new Factory(BigDecimal.valueOf(Math.pow(10, i+1)), upglv, asclv, gen, name);
        }
        asccount = autodata.getLong("asccount", 0);
        ascendmulty = autodata.getFloat("ascmulty", 0.4F);
    }
}

