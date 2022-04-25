package com.example.finpro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class counter extends Fragment {

    TextView display;
    Button add;
    Button dec;
    Button res;

    private Integer number;

    SharedPreferences instance;

    private void init(){
        number = Integer.parseInt(display.getText().toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                display.setText(number.toString());
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number--;
                display.setText(number.toString());
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 0;
                display.setText(number.toString());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Lol", "Warning Bro Breated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_counter, container, false);
        display = v.findViewById(R.id.number_display);
        add = v.findViewById(R.id.button_add);
        dec = v.findViewById(R.id.button_decrement);
        res = v.findViewById(R.id.button_reset);
        init();

        instance = getActivity().getApplicationContext().getSharedPreferences("counter", Context.MODE_PRIVATE);
        Integer value = getSavedCounter();
        display.setText(value.toString());
        number = value;
        return v;
    }

    public int getSavedCounter() {
        if(instance.contains("counter_data")) {
            return instance.getInt("counter_data", 0);
        }
        return 0;
    }
}