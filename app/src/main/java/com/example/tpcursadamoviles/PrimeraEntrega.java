package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrimeraEntrega extends AppCompatActivity {

    private int numero= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_entrega);
    }

    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
        super.onSaveInstanceState(guardaEstado);
        //lo "guardamos" en el Bundle
        guardaEstado.putInt("num", numero);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
        super.onRestoreInstanceState(recuperaEstado);
        //recuperamos el num del Bundle
        TextView numberView = (TextView) findViewById(R.id.textNumber);
        numero= recuperaEstado.getInt("num");
        //Seteamos el valor del EditText con el valor de nuestra cadena
        numberView.setText(String.valueOf(numero));
    }

    public void incrementarNumber(View view) {
        TextView numberView = (TextView) findViewById(R.id.textNumber);
        numero = numero + 1;
        numberView.setText(String.valueOf(numero));
    }

    public void resetNumber(View view) {
        TextView numberView = (TextView) findViewById(R.id.textNumber);
        numero = 0;
        numberView.setText(String.valueOf(numero));
    }
}

