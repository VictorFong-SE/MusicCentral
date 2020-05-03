package com.vfong3.musiccentral;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MusicCentral extends Service
{
    private Notification notification;
    private static final int NOTIFICATION_ID = 1; //keep track of itself

    private static String CHANNEL_ID = "Music Central";

    public static String[] titles;
    public static String[] artists;
    public static String[] urls;
    public ArrayList<Bitmap> bitmaps;


    @Override
    public void onCreate()
    {
        super.onCreate();

        titles = getResources().getStringArray(R.array.Titles);
        artists = getResources().getStringArray(R.array.Artists);
        urls = getResources().getStringArray(R.array.urls);

        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.summer));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.ukulele));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.creativeminds));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.anewbeginning));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.littleidea));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.jazzyfrenchy));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.happyrock));


        this.checkOreo();   //check if android running Oreo+ which needs notif channels.

        final Intent notificationIntent = new Intent(getApplicationContext(), MusicCentral.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .setContentTitle("Music Central Running")
                .setContentText("Tap to access Music Central")
                .setTicker("Music Central Running!")
                .setFullScreenIntent(pendingIntent, false)
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
        return binder;
    }


    private final myAIDL.Stub binder = new myAIDL.Stub()
    {
        public Bundle retrieveAllInfo() throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("song0", new Song(0, titles[0], artists[0], bitmaps.get(0), urls[0]));
            bundle.putParcelable("song1", new Song(1, titles[1], artists[0], bitmaps.get(1), urls[1]));
            bundle.putParcelable("song2", new Song(2, titles[2], artists[0], bitmaps.get(2), urls[2]));
            bundle.putParcelable("song3", new Song(3, titles[3], artists[0], bitmaps.get(3), urls[3]));
            bundle.putParcelable("song4", new Song(4, titles[4], artists[0], bitmaps.get(4), urls[4]));
            bundle.putParcelable("song5", new Song(5, titles[5], artists[0], bitmaps.get(5), urls[5]));
            bundle.putParcelable("song6", new Song(6, titles[6], artists[6], bitmaps.get(6), urls[6]));

            return bundle;
        }

        public Bundle retrieveSongInfo(int songNumber) throws RemoteException
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("song0", new Song(songNumber, titles[songNumber], artists[songNumber], bitmaps.get(songNumber), urls[songNumber]));
            return bundle;
        }

        public String retrieveURL(int songNumber) throws RemoteException
        {
            return urls[songNumber];
        }
    };
}
