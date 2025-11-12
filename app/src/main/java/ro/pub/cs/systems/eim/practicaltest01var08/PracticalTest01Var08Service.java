package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

public class PracticalTest01Var08Service extends Service{

    // Definim acțiunea pentru broadcast și cheile pentru date
    public static final String ACTION_UPDATE_UI = "ro.pub.cs.systems.eim.practicaltest01var08.ACTION_UPDATE_UI";
    public static final String KEY_VAL_ANSWER = "val_answer";

    private Handler handler;
    private static final long INTERVAL = 5000; // 5 secunde

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // generez mesajul
            String val_answer = "****e*";

            Log.d("MyService", "Broadcasting values: " + val_answer);

            // 2. Creează Intent-ul pentru broadcast
            Intent intent = new Intent(ACTION_UPDATE_UI);
            intent.putExtra(KEY_VAL_ANSWER, val_answer);

            // 3. Trimite broadcast-ul
            sendBroadcast(intent);

            // 4. Reprogramează executarea
            handler.postDelayed(this, INTERVAL);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // Inițializează handler-ul pe firul principal (sau un HandlerThread dacă preferi)
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "Service started.");
        handler.post(runnable);

        // START_STICKY asigură repornirea serviciului dacă e oprit de sistem
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "Service stopped.");
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
