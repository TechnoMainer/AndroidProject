package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static BigDecimal money;
    public static BigDecimal prevmoney;
    public int ascendmulty;

    ArrayList<Map<String, Object>> Factories = new ArrayList<>();
    static CustomAdapter adapter;

    ListView listView;
    static TextView mon;
    ImageView achivebutton;
    static FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        money = BigDecimal.valueOf(1000);
        fm = getSupportFragmentManager();
        prevmoney = money;
        listView = findViewById(R.id.listview);
        mon = findViewById(R.id.money);
        achivebutton = findViewById(R.id.achivment);
        mon.setText(String.valueOf(MainActivity.money.toBigInteger()));
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

        adapter = new CustomAdapter(facts, this);
        listView.setAdapter(adapter);

        ChangeThread changeThread = new ChangeThread();
        changeThread.start();
        FragmentTransaction ft = fm.beginTransaction();
        AchiveFrag af = new AchiveFrag();
        ft.hide(af);
        ft.commit();

        achivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!af.isAdded()) {
                    ft.replace(R.id.achivments, af);
                }
                else{
                    ft.remove(af);
                }
                try {
                    ft.commit();
                } catch (Exception e) {
                    throw new RuntimeException("Alredy Commited");
                }
            }
        });
    }

}

class ChangeThread extends Thread{

    @Override
    public void run(){
        while (true){
            if(!MainActivity.prevmoney.equals(MainActivity.money)){
                MainActivity.prevmoney = MainActivity.money;
                MainActivity.mon.setText(String.valueOf(MainActivity.money.toBigInteger()));
            }
        }
    }
}