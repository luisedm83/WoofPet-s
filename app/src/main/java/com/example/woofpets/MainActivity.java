package com.example.woofpets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView txt_email;
    TextView txt_password;
    Button button_ingresar;
    Button button_registro;
    Button button_olvido;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        button_ingresar = findViewById(R.id.button_enviar_String);
        button_registro = findViewById(R.id.button_registro);
        button_olvido = findViewById(R.id.button_olvido);

        mAuth = FirebaseAuth.getInstance();

        button_ingresar.setOnClickListener(view ->{
            loginUser();
        });
        button_registro.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, Registro.class));
        });
        button_olvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, RecuperarPassword.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void loginUser(){
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        if(TextUtils.isEmpty(email)) {
            txt_email.setError("Email no puede estar vacio");
            txt_email.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            txt_password.setError("Password no puede estar vacio");
            txt_password.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Logeo Satisfactorio", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MenuPrincipal.class));
                    }else {
                        Toast.makeText(MainActivity.this, "Logeo ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}