package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class TerceraEntrega extends AppCompatActivity {

    private Button start;
    private Button stop;
    private Button reset;
    private TextView contador;
    private int cont = 0;
    private boolean active = false;
    public final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_entrega);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        contador = (TextView) findViewById(R.id.contador);
        stop.setEnabled(false);

        class Inner extends AsyncTask<Integer, Integer, Void> {

            @Override
            protected Void doInBackground(Integer... integers) {
                active = true;
                // stop.setEnabled(true);
                while (active) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cont++;
                    Log.d(TAG, "Segudos : " + contador);
                }
                return null;
            }

        }
    }
}
