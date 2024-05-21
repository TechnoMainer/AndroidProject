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


    public static BigDecimal money;
    public static BigDecimal prevmoney;
    public int ascendmulty;

    ArrayList<Map<String, Object>> Factories = new ArrayList<>();
    static CustomAdapter adapter;

    ListView listView;
    static TextView  mon;
    ImageView achivebutton;
    FragmentManager fm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_automation, container, false);
        money = BigDecimal.valueOf(1000);
        //fm = getSupportFragmentManager();
        prevmoney = money;
        listView = view.findViewById(R.id.listview);
        mon = view.findViewById(R.id.money);
        achivebutton = view.findViewById(R.id.achivment);
        //BottomNavigationView navigation = view.findViewById(R.id.bottomNavigationView);

        mon.setText(String.valueOf(money.toBigInteger()));
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

        ChangeThread changeThread = new ChangeThread();
        changeThread.start();


        //ft.hide(af);


        achivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AchiveFrag af = new AchiveFrag();
                FragmentTransaction ft = fm.beginTransaction();
                if(!af.isAdded()) {
                    Log.d("DD", "opened");
                    ft.replace(R.id.achivments, af);
                    ft.commit();
                }
                else{
                    Log.d("DD", "closed");
//                    ft.remove(af);
                    //ft.commit();
                }
                try {
                    Log.d("DD", "commit");
                    //ft.commit();
                } catch (Exception e) {
                    throw new RuntimeException("Alredy Commited");
                }
            }
        });



        return view;
    }
}

class ChangeThread extends Thread{

    @Override
    public void run(){
        while (true){
            if(!Automation.prevmoney.equals(Automation.money)){
                Automation.prevmoney = Automation.money;
                Automation.mon.setText(String.valueOf(Automation.money.toBigInteger()));
            }
        }
    }
}