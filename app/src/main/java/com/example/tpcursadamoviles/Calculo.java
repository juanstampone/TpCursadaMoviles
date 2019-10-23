package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculo extends AppCompatActivity {
    private Button buttonDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        buttonDone = (Button) findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                float resultado= 0 ;
                char operador = getIntent().getCharExtra("Operacion",' ');
                TextView idValor1 = (TextView) findViewById(R.id.buttonValor1);
                TextView idValor2 = (TextView) findViewById(R.id.buttonValor2);
                int valor1 = Integer.parseInt(idValor1.getText().toString());
                int valor2 = Integer.parseInt(idValor2.getText().toString());
                if ( operador == '+')
                    resultado = valor1 + valor2;
                System.out.println("resultado" +resultado);
                replyIntent.putExtra("Resultado",resultado);
                setResult(Activity.RESULT_OK,replyIntent);
                finish();
            }
        });
    }
}
