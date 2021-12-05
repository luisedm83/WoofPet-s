package com.example.woofpets;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class RegistroMascotas extends AppCompatActivity {

    private EditText txt_nombrepet, txt_fecha, txt_raza, txt_tipo, txt_sexo;
    private Button button_Subir_foto, button_registrar_pets;


    //VARIABLES DE DATOS A REGISTRAR
    private String nombrepet;
    private String fecha;
    private String raza;
    private String tipo;
    private String sexo;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //ESTAS DOS LINEAS HACEN PARTE DEL CARGUE DE IMAGENES
    StorageReference mStorage;
    static final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txt_nombrepet = (EditText) findViewById(R.id.txt_nombrepet);
        txt_fecha = (EditText) findViewById(R.id.txt_fecha);
        txt_raza = (EditText) findViewById(R.id.txt_raza);
        txt_tipo = (EditText) findViewById(R.id.txt_tipo);
        txt_sexo = (EditText) findViewById(R.id.txt_sexo);
        button_Subir_foto = (Button) findViewById(R.id.button_Subir_foto);
        button_registrar_pets = (Button) findViewById(R.id.button_registrar_pets);

        button_registrar_pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombrepet = txt_nombrepet.getText().toString();
                fecha = txt_fecha.getText().toString();
                raza = txt_raza.getText().toString();
                tipo = txt_tipo.getText().toString();
                sexo = txt_sexo.getText().toString();

                if(!nombrepet.isEmpty() && !fecha.isEmpty() && !raza.isEmpty() && !tipo.isEmpty() && !sexo.isEmpty()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombrepet", nombrepet);
                    map.put("fecha", fecha);
                    map.put("raza", raza);
                    map.put("tipo", tipo);
                    map.put("sexo", sexo);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).child(nombrepet).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(RegistroMascotas.this, MenuPrincipal.class));
                                Toast.makeText(RegistroMascotas.this, "Registro Satisfactorio", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(RegistroMascotas.this, "ERROR! Registro NO Satisfactorio", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegistroMascotas.this, "ERROR! Hay campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ABRIR ALMACENAMIENTO DE FOTOS
        mStorage = FirebaseStorage.getInstance().getReference();
        button_Subir_foto = (Button) findViewById(R.id.button_Subir_foto);
        button_Subir_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    //CARGAR FOTO A FIREBASE
/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(RegistroMascotas.this, "Foto subio satisfactoriamente", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/

}