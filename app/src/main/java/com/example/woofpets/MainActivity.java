package com.example.woofpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woofpets.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método el botón
    public void Ingresar(View view){
        Intent ingresar = new Intent(this, MenuPrincipal.class);
        startActivity(ingresar);
    }
}