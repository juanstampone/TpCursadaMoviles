package com.example.tpcursadamoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TerceraEntrega extends AppCompatActivity {
/*** CONTADOR EN BACKGROUND , AL CERRAR LA APP RECUERDA TANTO EL VALOR DE SLEEP COMO LA CUENTA ***/
    private Button start;
    private Button stop;
    private Button reset;
    private TextView contador;
    private int cont = 0;
    private int sleep = 1;
    private EditText step;
    private boolean active = false;
    private Inner taskMain;
    public final static String SHARED_PREF = "sharedPrefer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_entrega);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        cont = sharedPreferences.getInt("CUENTA",0);
        sleep = sharedPreferences.getInt("SLEEP",1);
        String text = "CONTADOR: " + cont;
        contador = (TextView) findViewById(R.id.contador);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        contador.setText(text);
        reset.setEnabled(false);
        stop.setEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editorPreferences = sharedPreferences.edit();
        editorPreferences.putInt("SLEEP",sleep);
        editorPreferences.putInt("CUENTA",cont);
        editorPreferences.apply();
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_tercera_entrega);
        stop = (Button) findViewById(R.id.stop);
        stop.setEnabled(false);
        reset = (Button) findViewById(R.id.reset);
        reset.setEnabled(false);
    }*/


    @Override
    public void onBackPressed() { // si se preciono start y luego me fui por la flecha
        this.active = false;      // debo "matar" al doInBackground. De lo contrario, al ingresar no funciona.
        if (taskMain != null)
            taskMain.cancel(true);
        super.onBackPressed();
    }

    public void onReset(View view){
        cont = 0;
        sleep = 1;
        reset = (Button) findViewById(R.id.reset);
        contador = (TextView) findViewById(R.id.contador);
        reset.setEnabled(false);
        contador.setText("CONTADOR: 0");
    }

    public void onStart(View view){
        taskMain = new Inner();
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
            this.sleep = (valor * 1000);
        } else if (sleep == 1)
            this.sleep = (valor * 1000);

        System.out.println("VALOR DEL SLEEP : " + sleep);
        start.setEnabled(false);
        reset.setEnabled(false);
        stop.setEnabled(true);
        this.active = true;
        taskMain.execute();

    }

    public void onStop(View view){
            System.out.println("ENTROOO");
            String out = "Pausado , Cuenta Actual: " + cont;
            contador.setText(out);
            this.active = false;
            reset.setEnabled(true);
            stop.setEnabled(false);
            start.setEnabled(true);
            taskMain.cancel(true);
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
