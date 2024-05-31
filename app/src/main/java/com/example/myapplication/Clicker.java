package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.math.BigDecimal;

public class Clicker extends Fragment {

    ImageView click;

    BigDecimal income = BigDecimal.valueOf(1);
    BigDecimal previnc = BigDecimal.valueOf(1);
    Button button;

    public Clicker() {
    }
    public static Clicker newInstance(String param1, String param2) {
        Clicker fragment = new Clicker();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onDestroy(Bundle savedInstanceState) {
        savedInstanceState.putString("income", String.valueOf(income));
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clicker, container, false);
        click = view.findViewById(R.id.click);
        button = view.findViewById(R.id.upgincome);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.money.compareTo(income.multiply(BigDecimal.valueOf(10)))>=0){
                    MainActivity.money = MainActivity.money.subtract(income.multiply(BigDecimal.valueOf(10)));
                    income = income.add(previnc);
                    button.setText(String.valueOf(income.multiply(BigDecimal.valueOf(10))));
                }
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.money = MainActivity.money.add(income.multiply(Prestige.prestigemulti));
            }
        });
        return view;
    }
}