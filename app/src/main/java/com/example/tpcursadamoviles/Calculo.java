package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    }

    public void mostrarBotonTerminar(View view) {
        TextView idValor1 = (TextView) findViewById(R.id.buttonValor1);
        TextView idValor2 = (TextView) findViewById(R.id.buttonValor2);
        buttonDone = (Button) findViewById(R.id.buttonDone);
        if (!idValor1.getText().toString().isEmpty() && !idValor2.getText().toString().isEmpty()) {
            buttonDone.setEnabled(true);
        } else
            buttonDone.setEnabled(false);

    }

    public void calcularResultado(View view) {
        Intent replyIntent = new Intent();
        float resultado = 0;
        char operador = getIntent().getCharExtra("Operacion", ' ');
        TextView idValor1 = (TextView) findViewById(R.id.buttonValor1);
        TextView idValor2 = (TextView) findViewById(R.id.buttonValor2);
        float valor1 = Float.parseFloat(idValor1.getText().toString());
        float valor2 = Float.parseFloat(idValor2.getText().toString());
        if (operador == '+')
            resultado = valor1 + valor2;
        else if (operador == '-')
            resultado = valor1 - valor2;
        else if (operador == '/')
            resultado = valor1 / valor2;
        else
            resultado = valor1 * valor2;
        replyIntent.putExtra("Resultado", resultado);
        setResult(Activity.RESULT_OK, replyIntent);
        finish();

    }
}
