package com.example.tpcursadamoviles;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

//https://developer.android.com/guide/components/bound-services?hl=ES#java
public class ServiceMessenger extends Service {
//Clase para Bound Service (Messenger para procesos remotos)
    /**
     * Handler of incoming messages from clients.
     */
    public class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Messenger reply = msg.replyTo;
            Message msg2 = Message.obtain();

            int nro = (int)(Math.random()*100+1);
            msg2.arg1 = nro;
            try {
                reply.send(msg2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


}
