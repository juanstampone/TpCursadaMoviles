package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SegundaEntrega extends AppCompatActivity {

    private Button buttonMas;
    private Button buttonMenos;
    private Button buttonDiv;
    private Button buttonMult;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        SegundaEntrega.super.onActivityResult(requestCode,resultCode,data);
        String reply = data.getStringExtra("Resultado");
        System.out.println("Resultado Final: " + reply);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_entrega);


        buttonMas = (Button) findViewById(R.id.buttonMas);
        buttonMas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SegundaEntrega.this,Calculo.class);
                char oper = '+';
                intent.putExtra("Operacion",oper);
                startActivityForResult(intent,1);


            }
        });

    }
}
