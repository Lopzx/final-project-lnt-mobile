package com.example.finpro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class calc_luas extends Fragment {
    // Persegi
    EditText panjangSisiPersegi;
    Button calculatePersegi;
    TextView resultPersegi;

    // Segitiga
    EditText panjangAlasSegitiga;
    EditText tinggiSegitiga;
    Button calculateSegitiga;
    TextView resultSegitiga;

    // Lingkaran
    EditText jariLingkaran;
    Button calculateLingkaran;
    TextView resultLingkaran;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calc_luas, container, false);;
        //Persegi
        panjangSisiPersegi = v.findViewById(R.id.panjang_sisi_persegi);
        calculatePersegi = v.findViewById(R.id.calculate_persegi);
        resultPersegi = v.findViewById(R.id.result_persegi);

        //Segitiga
        panjangAlasSegitiga = v.findViewById(R.id.panjang_alas_segitiga);
        tinggiSegitiga = v.findViewById(R.id.tinggi_segitiga);
        calculateSegitiga = v.findViewById(R.id.calculate_segitiga);
        resultSegitiga = v.findViewById(R.id.result_segitiga);

        //Lingkaran
        jariLingkaran = v.findViewById(R.id.jari_lingkaran);
        calculateLingkaran = v.findViewById(R.id.calc_lingkaran);
        resultLingkaran = v.findViewById(R.id.result_lingkaran);

        calculatePersegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   resultPersegi.setText(Double.toString(Math.pow(Double.parseDouble(panjangSisiPersegi.getText().toString()),2)));
               } catch (Exception ex) {
                    Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
               }
            }
        });

        calculateSegitiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   Double panjangAlasVal = Double.parseDouble(panjangAlasSegitiga.getText().toString());
                   Double tinggiSegitigaVal = Double.parseDouble(tinggiSegitiga.getText().toString());
                   resultSegitiga.setText(String.format(Locale.US, "%.3f",(1d/2d) * panjangAlasVal * tinggiSegitigaVal));
               } catch (Exception ex) {
                   Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
               }
            }
        });

        calculateLingkaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double jari = Double.parseDouble(jariLingkaran.getText().toString());
                    resultLingkaran.setText(String.format(Locale.US, "%.3f",Math.PI * Math.pow(jari, 2)));
                } catch (Exception ex) {
                    Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}