package com.example.woofpets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RecuperarPassword extends AppCompatActivity {


    TextView txt_email;
    TextView login_recuperar, registro_recuperar;
    Button button_enviar_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        txt_email = findViewById(R.id.txt_email);
        login_recuperar = findViewById(R.id.login_recuperar);
        registro_recuperar = findViewById(R.id.registro_recuperar);
        button_enviar_String = findViewById(R.id.button_enviar_String);

        button_enviar_String.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    public void validate(){

        String email = txt_email.getText().toString().trim();
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt_email.setError("Correo Inválido");
            return;
        }

        sendEmail(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RecuperarPassword.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendEmail(String email){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String emailAddress = email;

        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RecuperarPassword.this, "Correo Enviado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RecuperarPassword.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(RecuperarPassword.this, "Correo inválido", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}