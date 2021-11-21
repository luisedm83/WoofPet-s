package com.example.woofpets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuPrincipal extends AppCompatActivity {


    Button button_mppal_registro_mascotas;
    Button button_mppal_mis_mascotas;
    Button button_mppal_ubicar_veterinarias;
    Button button_cerrar_sesion;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        button_mppal_registro_mascotas = findViewById(R.id.button_mppal_registro_mascotas);
        button_mppal_mis_mascotas = findViewById(R.id.button_mppal_mis_mascotas);
        button_mppal_ubicar_veterinarias = findViewById(R.id.button_mppal_ubicar_veterinarias);
        button_cerrar_sesion = findViewById(R.id.button_cerrar_sesion);

        mAuth = FirebaseAuth.getInstance();

        button_cerrar_sesion.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        }
    }

}