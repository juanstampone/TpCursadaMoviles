package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button siguiente;
    private Button segundaEntrega;
    private Button terceraEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        siguiente = (Button) findViewById(R.id.botonSig);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(MainActivity.this,PrimeraEntrega.class);
                startActivity(siguiente);
            }
        });

        segundaEntrega = (Button) findViewById(R.id.buttonSegEnt);
        segundaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(MainActivity.this,SegundaEntrega.class);
                startActivity(siguiente);
            }
        });

        terceraEntrega = (Button) findViewById(R.id.buttonTerEnt);
        terceraEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(MainActivity.this,TerceraEntrega.class);
                startActivity(siguiente);
            }
        });

    }
}
