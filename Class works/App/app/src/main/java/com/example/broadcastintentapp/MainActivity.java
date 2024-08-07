package com.example.broadcastintentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;

import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {
    MyReceiver receiver;
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
        configureReceiver();
    }
    @SuppressLint("UnsafeImplicitIntentLaunch")
    public void broadcast(View v)
    {
        Intent intent=new Intent();
        intent.setAction("com.example.broadcastintentapp");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
   @SuppressLint("NewApi")
   private void configureReceiver()
   {
       IntentFilter filter=new IntentFilter();
       filter.addAction("com.example.broadcastintentapp");
       receiver=new MyReceiver();
       registerReceiver(receiver,filter);
   }
   protected void onDestroy()
   {
       super.onDestroy();
       unregisterReceiver(receiver);
   }


}