package com.example.woofpets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    TextView txt_email_String;
    TextView txt_password_String;
    TextView login_return;
    TextView txt_olvido_return;
    Button button_registrar_string;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_email_String = findViewById(R.id.txt_email_String);
        txt_password_String = findViewById(R.id.txt_password_String);
        login_return = findViewById(R.id.login_return);
        button_registrar_string = findViewById(R.id.button_registrar_string);
        txt_olvido_return = findViewById(R.id.txt_olvido_return);

        mAuth = FirebaseAuth.getInstance();

        button_registrar_string.setOnClickListener(view ->{
            createUser();
        });

        login_return.setOnClickListener(view ->{
            startActivity(new Intent(Registro.this, MainActivity.class));
        });

        txt_olvido_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Registro.this, RecuperarPassword.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void createUser(){
        String email = txt_email_String.getText().toString();
        String password = txt_password_String.getText().toString();

        if(TextUtils.isEmpty(email)) {
            txt_email_String.setError("Email no puede estar vacio");
            txt_email_String.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            txt_password_String.setError("Password no puede estar vacio");
            txt_password_String.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Registro.this, "Registro Satisfactorio", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registro.this, MainActivity.class));
                    }else{
                        Toast.makeText(Registro.this, "Registro ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}