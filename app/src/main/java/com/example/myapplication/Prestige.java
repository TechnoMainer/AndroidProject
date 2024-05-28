package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.math.BigDecimal;

public class Prestige extends Fragment {

    public static BigDecimal prestigemulti = BigDecimal.valueOf(1);
    Button prestige;

    public Prestige() {
        // Required empty public constructor
    }
    public static Prestige newInstance(String param1, String param2) {
        Prestige fragment = new Prestige();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestige, container, false);
        prestige = view.findViewById(R.id.prestige);
        prestige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Automation.fac8.upglv>=1){
                    prestigemulti = prestigemulti.add(BigDecimal.valueOf(Automation.asccount));
                    MainActivity.money = BigDecimal.valueOf(0);
                    Automation.fac1 = new Factory(BigDecimal.valueOf(10), 10000);
                    Automation.fac2 = new Factory(BigDecimal.valueOf(100), 10000);
                    Automation.fac3 = new Factory(BigDecimal.valueOf(1000), 10000);
                    Automation.fac4 = new Factory(BigDecimal.valueOf(10000), 10000);
                    Automation.fac5 = new Factory(BigDecimal.valueOf(100000), 10000);
                    Automation.fac6 = new Factory(BigDecimal.valueOf(1000000), 1000);
                    Automation.fac7 = new Factory(BigDecimal.valueOf(10000000), 10000);
                    Automation.fac8 = new Factory(BigDecimal.valueOf(100000000), 10000);
                }
            }
        });
        return view;
    }
}