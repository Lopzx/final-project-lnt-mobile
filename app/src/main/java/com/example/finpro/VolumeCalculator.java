package com.example.finpro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class VolumeCalculator extends Fragment {
    // Balok
    EditText panjangBalok;
    EditText lebarBalok;
    EditText tinggiBalok;
    Button calculateBalok;
    TextView resultBalok;

    // Piramida
    EditText luasAlasPiramida;
    EditText tinggiPiramida;
    Button calculatePiramida;
    TextView resultPiramida;

    // Tabung
    EditText jariTabung;
    EditText tinggiTabung;
    Button calculateTabung;
    TextView resultTabung;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_volume_calculator, container, false);

        panjangBalok = v.findViewById(R.id.panjang_balok);
        lebarBalok = v.findViewById(R.id.lebar_balok);
        tinggiBalok = v.findViewById(R.id.tinggi_balok);
        calculateBalok = v.findViewById(R.id.calculate_balok);
        resultBalok = v.findViewById(R.id.result_balok);

        luasAlasPiramida = v.findViewById(R.id.luasalas_piramida);
       tinggiPiramida = v.findViewById(R.id.tinggi_piramida);
        calculatePiramida = v.findViewById(R.id.calculate_piramida);
        resultPiramida = v.findViewById(R.id.result_piramida);

        jariTabung = v.findViewById(R.id.jari_tabung);
       tinggiTabung = v.findViewById(R.id.tinggi_tabung);
        calculateTabung = v.findViewById(R.id.calculate_tabung);
        resultTabung = v.findViewById(R.id.result_tabung);


        calculateBalok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Double panjangBalokVal = Double.parseDouble(panjangBalok.getText().toString());
                    Double lebarBalokVal = Double.parseDouble(lebarBalok.getText().toString());
                    Double tinggiBalokVal = Double.parseDouble(tinggiBalok.getText().toString());
                    resultBalok.setText(Double.toString(panjangBalokVal * lebarBalokVal * tinggiBalokVal));
                } catch (Exception ex) {
                    Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
                }

            }
        });

        calculatePiramida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double luasAlasPiramidaVal = Double.parseDouble(luasAlasPiramida.getText().toString());
                    Double tinggiPiramidaVal = Double.parseDouble(tinggiPiramida.getText().toString());
                    resultPiramida.setText(Double.toString(1d/3d * Math.pow(luasAlasPiramidaVal,2) * tinggiPiramidaVal));
                } catch (Exception ex) {
                    Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
                }
            }
        });

        calculateTabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double jariTabungVal = Double.parseDouble(jariTabung.getText().toString());
                    Double tinggiTabungVal = Double.parseDouble(tinggiTabung.getText().toString());
                    resultTabung.setText(Double.toString(Math.PI * Math.pow(jariTabungVal,2) * tinggiTabungVal));
                } catch (Exception ex) {
                    Toast.makeText(getContext(),"Input tidak boleh kosong atau mengandung huruf", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}