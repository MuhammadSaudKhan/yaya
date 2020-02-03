package com.saud.app.yaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.saud.app.yaya.Model.exercise;

import java.util.ArrayList;

import static com.saud.app.yaya.App.CHANNEL_2_ID;

public class ActivityTimer extends AppCompatActivity {
    ProgressBar CountProgressBar;
    TextView time,exercise_name;
    private static int exCount=0;
    private NotificationManagerCompat notificationManager;
    ArrayList<exercise> myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initUI();
        notificationManager = NotificationManagerCompat.from(this);
        startService(new Intent(this, BroadcastService.class));
        myList = (ArrayList<exercise>) getIntent().getSerializableExtra("list");
        exercise_name.setText(myList.get(exCount).getTitle());
    }

    public static class BroadcastService extends Service {

        private final static String TAG = "BroadcastService";
        public static final String COUNTDOWN_BR = "your_package_name.countdown_br";
        Intent bi = new Intent(COUNTDOWN_BR);
        @Override
        public void onCreate() {
            super.onCreate();

            Log.i(TAG, "Starting timer...");
            sendBroadcast(bi);
        }
        @Override
        public void onDestroy() {
            Log.i(TAG, "Timer cancelled");
            super.onDestroy();
        }
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent arg0) {
            return null;
        }
    }
    private void initUI() {
        CountProgressBar=findViewById(R.id.CountProgressBar);
        time=findViewById(R.id.timer);
        exercise_name=findViewById(R.id.exercise_name);
    }
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };
    private void updateGUI(Intent intent) {
        exercise ex=myList.get(exCount);
        final int progressMax = 50;
        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.workout)
                .setContentTitle("Exercise "+ex.getTitle())
                .setContentText("Progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, 0, true);

        final int[] count = {1};
        new CountDownTimer(50000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(count[0]));
                count[0]++;
                notification.setProgress(progressMax, count[0], false);
                notification.setContentText(count[0]+" seconds");
                notificationManager.notify(2, notification.build());
            }
            @Override
            public void onFinish() {
                notification.setContentText("Exercise Completed")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManager.notify(2, notification.build());
                stopService(new Intent(ActivityTimer.this, BroadcastService.class));
            }
        }.start();

    }


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i("MainActivity", "Registered broacast receiver");
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i("MainActivity", "Unregistered broacast receiver");
    }
    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        Log.i("Timer Activity", "Stopped service");
        super.onDestroy();
    }
}
