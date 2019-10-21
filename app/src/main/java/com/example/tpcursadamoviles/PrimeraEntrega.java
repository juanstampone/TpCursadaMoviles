package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrimeraEntrega extends AppCompatActivity {

    private int number = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_entrega);
    }

    public void incrementarNumber(View view) {
        TextView numberView = (TextView) findViewById(R.id.textNumber);
        number = number + 1;
        numberView.setText(String.valueOf(number));
    }

    public void resetNumber(View view) {
        TextView numberView = (TextView) findViewById(R.id.textNumber);
        number = 0;
        numberView.setText(String.valueOf(number));
    }
}

