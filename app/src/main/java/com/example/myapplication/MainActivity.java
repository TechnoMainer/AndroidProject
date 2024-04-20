package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    SimpleAdapter simpleAdapter;

    ListView listView;
    static TextView mon;

    String[] from = {"income", "upglv", "upgcost", "name"};

    int[] to = {R.id.income, R.id.lv, R.id.upg, R.id.Name};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        money = BigDecimal.valueOf(0);
        prevmoney = money;
        listView = findViewById(R.id.listview);
        mon = findViewById(R.id.money);
        Factory fac1 = new Factory(BigDecimal.valueOf(10), 10000);
        Factory fac2 = new Factory(BigDecimal.valueOf(100), 10000);
        Factory fac3 = new Factory(BigDecimal.valueOf(1000), 10000);
        Factory fac4 = new Factory(BigDecimal.valueOf(10000), 10000);
        Factory fac5 = new Factory(BigDecimal.valueOf(100000), 10000);
        Factory fac6 = new Factory(BigDecimal.valueOf(1000000), 10000);
        Factory fac7 = new Factory(BigDecimal.valueOf(10000000), 10000);
        Factory fac8 = new Factory(BigDecimal.valueOf(100000000), 10000);

        Factory[] facts = {fac1, fac2, fac3, fac4, fac5, fac6, fac7, fac8};

        for(int i = 0; i<facts.length; i++){
            HashMap f = new HashMap();

            f.put("income", facts[i].income);
            f.put("upglv", facts[i].upglv);
            f.put("upgcost", facts[i].upgcost);
            f.put("name", facts[i].name);
            f.put("factid", facts[i]);

            Factories.add(f);
        }


        simpleAdapter = new SimpleAdapter(this, Factories, R.layout.item, from, to);
        listView.setAdapter(simpleAdapter);

        ChangeThread changeThread = new ChangeThread();
        changeThread.start();


    }
}

class ChangeThread extends Thread{

    @Override
    public void run(){
        while (true){
            if(!MainActivity.prevmoney.equals(MainActivity.money)){
                MainActivity.prevmoney = MainActivity.money;
                MainActivity.mon.setText(String.valueOf(MainActivity.money));
            }
        }
    }
}