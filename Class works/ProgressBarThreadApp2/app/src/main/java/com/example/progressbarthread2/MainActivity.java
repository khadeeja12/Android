package com.example.progressbarthread2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {
    private TextView progressText;
    private Button button;
    private ProgressBar progressBar;
    //create counter variable
    private int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        progressText=findViewById(R.id.textView);
        button=findViewById(R.id.button);
    }
        private abstract class NewClass extends AsyncTask<>
        {
        protected void onPreExecute(){
            //UI updation
            button.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        protected String doInBackground(Void... voids){
            for (progress = 0; progress <= 100; progress += 1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

}