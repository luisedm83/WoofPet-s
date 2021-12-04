package com.example.woofpets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistroMascotas extends AppCompatActivity {

    TextView nombrepet, fecha, raza, tipo, sexo;
    Button button_registrar_pets;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);
    }
}