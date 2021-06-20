package spm.androidworld.all.services.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * This class exposes the remote service to the client
 */
public class AdditionService extends Service {
    private static final String TAG = "AdditionService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /*@Override
    public IBinder onBind(Intent intent) {

        return new IAdditionService.Stub() {
            *//**
             * Implementation of the add() method
             *//*
            public int add(int value1, int value2) throws RemoteException {
                Log.d(TAG, String.format("AdditionService.add(%d, %d)", value1, value2));
                return value1 + value2;
            }

        };
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
