package com.example.tpcursadamoviles;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class ServiceIntent extends IntentService {

    public static final String PROGRESO = "nroIteration";
    public static final String FIN = "endIntentService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public ServiceIntent() {

        super("ServiceIntent");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("OnReceive" ,   "OnHandledINtent");
        for (int i=0; i<=4; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent bc = new Intent();
            bc.setAction(PROGRESO);
            bc.putExtra("iteration", i);
            sendBroadcast(bc);
        }
        Log.d("OnReceive" ,   "TERMINOONHANDLEINTENT");

        Intent bc = new Intent();
        bc.setAction(FIN);
        sendBroadcast(bc);
    }
}
