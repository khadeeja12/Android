package com.example.progressthread;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView progressText;
    private Button button;
    private ProgressBar progressBar;
    //create counter variable
    private int progress=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //initialize widgets
        progressBar=findViewById(R.id.progressBar);
        progressText=findViewById(R.id.textView);
        button=findViewById(R.id.button);


    }
    public void submit(View v){
        button.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        //start the worker thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (progress = 0; progress <= 100; progress += 1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //update textview and progressbar :Ui thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //code for ui updation
                            progressBar.setProgress(progress);
                            progressText.setText("progress: " + progress + "%");
                        }
                    });// end Ui thread
                }//end for loop
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setEnabled(true);
                        progressText.setText("COMPLETED");
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

        }).start();
    }
}