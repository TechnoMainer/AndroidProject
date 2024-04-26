package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomAdapter extends BaseAdapter{
    private Context context;

    Factory[] factories;

    public CustomAdapter(Factory[] factories, Context context) {
        this.context = context;
        this.factories = factories;
    }

    @Override
    public int getCount() {
        return factories.length;
    }

    @Override
    public Object getItem(int position) {
        return factories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, null);
        }

        TextView income = (TextView) view.findViewById(R.id.income);
        TextView upglv = (TextView) view.findViewById(R.id.lv);
        TextView name = (TextView) view.findViewById(R.id.Name);

        Button upgbtn = (Button) view.findViewById(R.id.upg);
        Button ascbtn = (Button) view.findViewById(R.id.asc);

        upgbtn.setText(String.valueOf(factories[position].upgcost));
        income.setText(String.valueOf(factories[position].income));
        upglv.setText(String.valueOf(factories[position].upglv));
        name.setText(String.valueOf(factories[position].name));

        Factory factory = (Factory) factories[position];

        upgbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(factory).upgrade();
                MainActivity.adapter.notifyDataSetChanged();

            }
        });
        ascbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(factory).ascending();
                MainActivity.adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}