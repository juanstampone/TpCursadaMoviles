package com.example.tpcursadamoviles;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CuartaEntrega extends AppCompatActivity {
    private TextView textViewIs;
    private TextView textS;
    private TextView textBound;
    private boolean isBound = false;

    Messenger messegeService = null;
    final Messenger mMessenger = new Messenger(new IncomingHandler());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("INICIO");

        setContentView(R.layout.activity_cuarta_entrega);
        textViewIs = (TextView) findViewById(R.id.textViewIS);
        textS = (TextView) findViewById(R.id.textS);
        textBound = (TextView) findViewById(R.id.textBS);
        textS.setText("Service: ");
        textViewIs.setText("Intent Service: ");
        textBound.setText("Service Bound: ");
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceIntent.PROGRESO);
        filter.addAction(ServiceIntent.FIN);
        filter.addAction(Services.ITERACION);
        LocalReceiver lrcv = new LocalReceiver();
        registerReceiver(lrcv, filter);
    }

    public void intent_receiver (View view){
        Intent intent = new Intent(this, ServiceIntent.class);
        startService(intent);
       // System.out.println("En intent Service");
    }

    public void service_receiver(View view) {
        textS.setText("Service: ");
        Intent intent = new Intent(this, Services.class);
        for (int i = 0; i <= 4; i++) {
            intent.putExtra("iteration", i);
            startService(intent);
        }

    }

   public void service_bound_receiver(View view) {
       if(!isBound) {
           setBindService();
           Toast.makeText(CuartaEntrega.this, "Binded Service", Toast.LENGTH_SHORT).show();
       }
       else {
           doUnbindService();
           textBound.setText("Bound Service " );
           Toast.makeText(CuartaEntrega.this, "Unbinded Service", Toast.LENGTH_SHORT).show();
       }
    }

    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ServiceIntent.PROGRESO)) {
                Log.d("OnReceive" ,   "En ServiceIntentIF");
                int iteration = intent.getIntExtra("iteration", 0);
                textViewIs.setText("Iteration: " + String.valueOf(iteration));
            }
            else if(intent.getAction().equals(ServiceIntent.FIN)) {
                Toast.makeText(CuartaEntrega.this, "EndIntentService", Toast.LENGTH_SHORT).show();
            } else if (intent.getAction().equals(Services.ITERACION)) {
                int iteration = intent.getIntExtra("iteration", 0);
                int id = intent.getIntExtra("id",0);
                Log.d("Iteration; " , String.valueOf(iteration) + " id:" + String.valueOf(id));
                textS.setText( textS.getText() + String.valueOf(iteration) + ",");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
        super.onSaveInstanceState(guardaEstado);
        String ResIS = ((TextView) findViewById(R.id.textViewIS)).toString();
        String ResS = ((TextView) findViewById(R.id.textS)).toString();
        String ResBS = ((TextView) findViewById(R.id.textBS)).toString();
        guardaEstado.putString("ResIS", ResIS);
        guardaEstado.putString("ResS", ResS);
        guardaEstado.putString("ResBS", ResBS);
    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
        super.onRestoreInstanceState(recuperaEstado);
        TextView ResIS = ((TextView) findViewById(R.id.textViewIS));
        TextView ResS = ((TextView) findViewById(R.id.textS));
        TextView ResBS = ((TextView) findViewById(R.id.textBS));
        ResIS.setText(recuperaEstado.getString("ResIS"));
        ResS.setText(recuperaEstado.getString("ResS"));
        ResBS.setText(recuperaEstado.getString("ResBS"));
    }

    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("handleMessage");
            if (msg != null)
                textBound.setText("Resultado messenger: " + msg.arg1);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            //System.out.println("CONNECTED--------------------------");
            messegeService = new Messenger(service);
            isBound = true;
            if (messegeService != null){
                Message msg = Message.obtain();
               // System.out.println("OnServiceConnected");
                msg.replyTo = mMessenger;
                try {
                    messegeService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("OnserviceConnected");

        }


        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            messegeService= null;
            isBound = false;
        }
    };

    void setBindService() {
        Intent intent = new Intent(this, ServiceMessenger.class);
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        isBound = true;
        System.out.println("SetBindService");
    }

    void doUnbindService() {
        if (isBound) {
            // Detach our existing connection.
            unbindService(connection);
            isBound = false;
        }
    }

}