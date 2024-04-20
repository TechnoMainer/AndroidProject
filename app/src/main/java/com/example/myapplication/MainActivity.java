package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public static BigDecimal money;
    public static BigDecimal prevmoney;
    public int ascendmulty;

    static TextView mon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mon = findViewById(R.id.textView);
        money = BigDecimal.valueOf(0);
        mon.setText(String.valueOf(money));
        Factory fac1 = new Factory(BigDecimal.valueOf(10), 10*1000);
    }
}