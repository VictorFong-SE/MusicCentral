package com.vfong3.musiccentral;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MusicCentral extends Service
{
    private Notification notification;
    private static final int NOTIFICATION_ID = 1; //keep track of itself

    private static String CHANNEL_ID = "Music Central";


    @Override
    public void onCreate()
    {
        super.onCreate();

        this.checkOreo();   //check if android running Oreo+ which needs notif channels.

        final Intent notificationIntent = new Intent(getApplicationContext(),MusicCentral.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .setContentTitle("Music Central Running")
                .setContentText("Tap to access Music Central")
                .setTicker("Music Central Running!")
                .setFullScreenIntent(pendingIntent,false)
                .build();

        startForeground(NOTIFICATION_ID, notification);

    }


    private void checkOreo()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Music Central notification";
            String description = "Channel for acquiring song information";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
