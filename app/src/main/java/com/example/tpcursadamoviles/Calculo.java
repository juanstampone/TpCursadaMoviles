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


public class Calculo extends AppCompatActivity{
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
    }

    @Override
    public void onBackPressed() {
        Intent replyIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, replyIntent);
        finish();
    }

    public void mostrarBotonTerminar(View view) {
        buttonDone = (Button) findViewById(R.id.buttonDone);
        if (checkCampos()) {
            buttonDone.setEnabled(true);
        } else
            buttonDone.setEnabled(false);
        buttonDone.setEnabled(true);
    }

    private boolean checkCampos(){
        TextView idValor1 = (TextView) findViewById(R.id.buttonValor1);
        TextView idValor2 = (TextView) findViewById(R.id.buttonValor2);
        return (!idValor1.getText().toString().isEmpty() && !idValor2.getText().toString().isEmpty());
    }

    public void calcularResultado(View view) {
        Intent replyIntent = new Intent();
        float resultado = 0;
        char operador = getIntent().getCharExtra("Operacion", ' ');
        if ( checkCampos()){
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
            buttonDone.setEnabled(true); // en la compu se puede saltear con el backspace y no toma el done.
            finish();
        }else {
            buttonDone.setEnabled(false);
        }
    }
}
