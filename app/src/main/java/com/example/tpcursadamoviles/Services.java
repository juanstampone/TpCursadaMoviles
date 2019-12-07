package com.example.tpcursadamoviles;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import android.os.Process;


public class Services extends Service {

    public static final String ITERACION = "iterationAction";

    private Looper serviceLooper;
    private ServiceHandler serviceHandler;


    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {

            System.out.println("MSG arg1: " + msg.arg1 + " MSG arg2: "+ msg.arg2);
            Intent bc = new Intent();
            bc.setAction(ITERACION);
            bc.putExtra("iteration", msg.arg2);
            bc.putExtra("id", msg.arg1);
            sendBroadcast(bc);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {

        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        Toast.makeText(this, "SERVICE START", Toast.LENGTH_SHORT).show();
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
        //System.out.println("FINISH ONCREATE SERVICE");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        // un thread por cada iteracion del for.
        final int iteration = intent.getIntExtra("iteration", 0);

        System.out.println("starId: "+ startId + "iteration: " + iteration + " flags : " + flags );

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job

        new Thread(new Runnable() {
            public void run() {
                Message msg = serviceHandler.obtainMessage();
                msg.arg1 = startId;
                msg.arg2 = iteration;
                serviceHandler.sendMessage(msg);
            }
        }).start();

       // System.out.println("FINISH ON START COMMAND");

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        //System.out.println("ON BIND SERVICE");

        return null;
    }

    @Override
    public void onDestroy() {
        //System.out.println("ON BIND SERVICE");
        Toast.makeText(this, "SERVICE DONE", Toast.LENGTH_SHORT).show();
    }
}
