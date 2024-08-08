package com.example.gridview;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        GridView grid =findViewById(R.id.grid);

        String item[]={"java","Python","R Programming","Android"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,item);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item_selected=adapter.getItem(i);
                Toast.makeText(MainActivity.this,"You selected : "+item_selected,Toast.LENGTH_SHORT).show();
            }
        });
    }
}