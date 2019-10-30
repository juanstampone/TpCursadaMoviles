package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundaEntrega extends AppCompatActivity {

    private Button buttonMas;
    private Button buttonMenos;
    private Button buttonDiv;
    private Button buttonMult;
    float resultadoOperacion;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        SegundaEntrega.super.onActivityResult(requestCode,resultCode,data);
        TextView numberResultado = (TextView) findViewById(R.id.textResultado);
        if (data.getExtras() != null && resultCode == RESULT_OK) {
            resultadoOperacion = data.getFloatExtra("Resultado", 0);
            numberResultado.setText(String.valueOf(resultadoOperacion));
        }else
            numberResultado.setText("Operacion cancelada");
    }

    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
        super.onSaveInstanceState(guardaEstado);
        //lo "guardamos" en el Bundle
        guardaEstado.putFloat("resultado", resultadoOperacion);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
        super.onRestoreInstanceState(recuperaEstado);
        //recuperamos el num del Bundle
        TextView numberResultado = (TextView) findViewById(R.id.textResultado);
        resultadoOperacion = recuperaEstado.getFloat("resultado");
        //Seteamos el valor del EditText con el valor de nuestra cadena
        numberResultado.setText(String.valueOf(resultadoOperacion));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_entrega);
    }

    public void sumar(View view) {
        Intent intent = new Intent(SegundaEntrega.this,Calculo.class);
        char oper = '+';
        intent.putExtra("Operacion",oper);
        startActivityForResult(intent,1);
    }

    public void restar(View view) {
        Intent intent = new Intent(SegundaEntrega.this,Calculo.class);
        char oper = '-';
        intent.putExtra("Operacion",oper);
        startActivityForResult(intent,1);
    }

    public void dividir(View view) {
        Intent intent = new Intent(SegundaEntrega.this,Calculo.class);
        char oper = '/';
        intent.putExtra("Operacion",oper);
        startActivityForResult(intent,1);
    }

    public void multiplicar(View view) {
        Intent intent = new Intent(SegundaEntrega.this,Calculo.class);
        char oper = '*';
        intent.putExtra("Operacion",oper);
        startActivityForResult(intent,1);
    }
}
