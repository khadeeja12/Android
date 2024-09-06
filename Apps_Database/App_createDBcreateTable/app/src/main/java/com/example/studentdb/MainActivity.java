package com.example.studentdb;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private EditText edit1,edit2,edit3;
  private DBHandler dbHandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edit1=findViewById(R.id.text_name);
        edit2=findViewById(R.id.text_course);
        edit3=findViewById(R.id.text_sem);
        dbHandler=new DBHandler(MainActivity.this);
    }
    public void  newStudent(View v){
        //get the values from edittexts
        String studentName=edit1.getText().toString();
        String studentCourse=edit2.getText().toString();
        String studentSemester= String.valueOf(Integer.parseInt(edit3.getText().toString()));
        dbHandler.addNewStudent(studentName,studentCourse, Integer.parseInt(studentSemester));
        Toast.makeText(MainActivity.this,"Student is added",Toast.LENGTH_SHORT).show();
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }

}