package com.example.customgridapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String[] items = {"Home","Contact","Account","Message"};
    int [] ImageIds={R.drawable.home,R.drawable.contact,R.drawable.account,R.drawable.message};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.grid);
        CustomAdapter adapter=new CustomAdapter(MainActivity.this,items,ImageIds);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text=items[i];
                Toast.makeText(MainActivity.this,"You selected : "+text,Toast.LENGTH_SHORT).show();
            }
        }));

    }
}