package com.example.alarmclock;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import java.util.Calendar;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void setAlarm(View v)
    {
      Calendar calendar=Calendar.getInstance();
      int hour=calendar.get(Calendar.HOUR_OF_DAY);
      int minute=calendar.get(Calendar.MINUTE);

      TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
          @Override
          public void onTimeSet(TimePicker timePicker, int i, int i1) {
              alarm(i,i1);
          }
      },hour,minute,true);
      timePickerDialog.show();
    }
    private void alarm(int hourOfDay,int minute){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);

        Intent intent=new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarm=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(alarm!=null)
        {
            alarm.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
    }

    public void stopAlarm(View v)
    {
      AlarmReceiver.stopRingtone();
    }
}