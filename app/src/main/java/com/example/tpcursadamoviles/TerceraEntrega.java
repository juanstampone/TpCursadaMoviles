package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TerceraEntrega extends AppCompatActivity {

    private Button start;
    private Button stop;
    private Button reset;
    private TextView contador;
    private int cont = 0;
    private int sleep = 1;
    private EditText step;
    private boolean active = false;
    public final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_entrega);
        System.out.println("E¡REORMPER");
        stop = (Button) findViewById(R.id.stop);
        stop.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        this.onStop();
        super.onBackPressed();
    }

    public void onReset(View view){
        cont = 0;
        stop = (Button) findViewById(R.id.stop);
        stop.setEnabled(false);
        contador = (TextView) findViewById(R.id.contador);
        contador.setText("CONTADOR: ");
    }

    public void onStart(View view){
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        contador = (TextView) findViewById(R.id.contador);
        step = (EditText) findViewById(R.id.espera);
        System.out.println("VALOR DEL STEP " + step.getText());
        int valor = 1;
        System.out.println("------------------------------ " + step.getText());

        if (step.length() > 0) {
            valor = Integer.valueOf(step.getText().toString());
            System.out.println(" Entro y nidufuci------------------------------ " + valor);
        }

        if ( valor <= 0) {
            valor = 1;
        }
        this.sleep =  (valor * 1000);
        System.out.println("VALOR DEL SLEEP : " + sleep);
        start.setEnabled(true);
        reset.setEnabled(false);
        stop.setEnabled(true);
        this.active = true;
        new Inner().execute();
    }

    public void onStop(View view){
        if (active) {
            this.active = false;
            reset.setEnabled(true);
            stop.setEnabled(false);
        }
        else {
            this.active = true;
            stop.setEnabled(true);
            reset.setEnabled(false);
        }
    }


    private class Inner extends AsyncTask<Integer, Integer, String> { //parametros, progresos, resultados

        @Override
        protected String doInBackground(Integer... integers) {
            // stop.setEnabled(true);
            synchronized (this) {
                System.out.println("VALOR DEL SLEEP : " + sleep);

                while (active) {
                    try {
                        Thread.sleep(sleep);
                        cont++;
                        publishProgress(cont);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            String out = "Pausado , Cuenta Actual: " + cont;
            return out;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            contador.setText(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            contador.setText("CONTADOR: " + progress);
        }
    }

}
