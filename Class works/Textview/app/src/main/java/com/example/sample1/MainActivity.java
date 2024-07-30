package com.example.sample1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        tv.setText("Text changed");
    }

    public void buttonClick(View v)
    {
        EditText et= findViewById(R.id.et1);
        TextView tv = findViewById(R.id.tv1);
        Editable s =et.getText();
        tv.setText(s);
    }
}