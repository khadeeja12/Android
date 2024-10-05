package com.example.mediaplayblackapp;

import android.app.Service;  //base class to implement service
import android.content.Intent;
import android.os.IBinder; //when we use bound service
import android.media.MediaPlayer;
import android.provider.Settings; //access to settings for the default ringtone

public class MediaService extends Service {
    private MediaPlayer player;

    public MediaService() {
    }

    public int onStartCommand(Intent intent, int flags, int start_id) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                switch (action) {
                    case "START":
                        if (player == null) {
                            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
                            player.setLooping(true);
                        }
                        player.start();
                        break;
                    case "PAUSE":
                        if (player != null && player.isPlaying()) {
                            player.pause();
                        }
                        break;
                    case "RESUME":
                        if (player != null && !player.isPlaying()) {
                            player.start();
                        }
                        break;
                }
            }
        }
        return START_STICKY;
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(player!=null)
        {
            //release media player
            player.release();
            player=null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}