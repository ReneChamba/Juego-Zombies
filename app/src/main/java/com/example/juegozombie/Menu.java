package com.example.juegozombie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity  implements View.OnClickListener {


    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initApp();

    }

    @Override
    protected void onStart() {
        usuarioLogeado();
        super.onStart();
    }

    private void initApp() {
        findByWidget();
        setListenerClick();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }


    private void findByWidget() {
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
    }

    private void setListenerClick() {
        btnCerrarSesion.setOnClickListener(this);
    }

    private void usuarioLogeado() {

        if (user != null) {
            Toast.makeText(this, "en linea", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == btnCerrarSesion.getId()){
            cerrarSesion();
        }
    }
    private void cerrarSesion(){
        auth.signOut();
        startActivity(new Intent(Menu.this, MainActivity.class));
        Toast.makeText(this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();

    }
}