package com.example.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.widget.Toast;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
public class AlarmReceiver extends BroadcastReceiver {
    private static Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast.makeText(context, "Wake up", Toast.LENGTH_SHORT).show();

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            Uri alarmuri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        //play alarm
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

    }
    public static void stopRingtone(){
      if(ringtone!=null && ringtone.isPlaying())
      {
          ringtone.stop();
      }
    }

}