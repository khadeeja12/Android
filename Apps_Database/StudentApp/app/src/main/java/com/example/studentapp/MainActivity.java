package com.example.studentapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

     public EditText edit1,edit2,edit3;
     public DBHandler dbHandler;
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
        edit1=findViewById(R.id.et1);
        edit2=findViewById(R.id.et2);
        edit3=findViewById(R.id.et3);
        dbHandler= new DBHandler(MainActivity.this);

    }
    public void newstudent(View v)
    {
        String studentName=edit1.getText().toString();
        String studentCourse=edit2.getText().toString();
        int studentSemester=Integer.parseInt(edit3.getText().toString());
        dbHandler.addNewStudent(studentName,studentCourse, String.valueOf(studentSemester));
        // validating if any of the text fields are empty or not.
        if (studentName.equals("") || studentCourse.equals("") || String.valueOf(studentSemester).equals("")) {
            Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        //insert data to the database through the content provider (without DBHandler)
        //class to add values in the database
        ContentValues values = new ContentValues();
        //values.put(column_name,data)
        values.put("name", studentName);
        values.put("course", studentCourse);
        values.put("semester", studentSemester);

        // inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        //getContentResolver(): The ContentResolver is responsible for interacting with Content Providers, allowing for operations such as querying, inserting, updating, and deleting data.
        //MyContentProvider.CONTENT_URI:static constant that represents the URI of the Content Provider where the new data will be inserted.
        Toast.makeText(MainActivity.this, "Student is added ", Toast.LENGTH_LONG);
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }

    public void lookupStudent(View v)
    {
        TextView resultView= (TextView) findViewById(R.id.display_tv);


        // creating a cursor object of the content URI to display all students
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.studentapp.provider/student_details"), null, null, null, null);

        // iteration of the cursor to print whole table
        if(cursor.moveToFirst()) {
            //build a string to add each data
            StringBuilder studentData=new StringBuilder();
            while (!cursor.isAfterLast()) {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String course = cursor.getString(2);
                int semester = Integer.parseInt(cursor.getString(3));
                studentData.append("ID:").append(id);
                studentData.append("Name:").append(name);
                studentData.append("Course:").append(course);
                studentData.append("Semester:").append(semester).append("\n");
                //move to next record
                cursor.moveToNext();
            }
            resultView.setText(studentData);
        }
        else {
            resultView.setText("No Records Found");
        }
    }

    public void removeStudent(View v)
    {
        //no changes: delete students through DBHandler
        String studentName=edit1.getText().toString();
        boolean result=dbHandler.deleteStudent(studentName);
        if (result)
        {
            Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_SHORT).show();
            edit1.setText("");
            edit2.setText("");
            edit3.setText("");
        }
        else
            Toast.makeText(MainActivity.this,"No Match Found",Toast.LENGTH_SHORT).show();
    }

    public void modifyStudentDetails(View v)
    {
        //no changes: update records through DBHandler
        String studentName=edit1.getText().toString();
        String studentCourse=edit2.getText().toString();
        int studentSemester=Integer.parseInt(edit3.getText().toString());
        dbHandler.deleteStudent(studentName);
        Toast.makeText(MainActivity.this,"Record Updated",Toast.LENGTH_SHORT).show();
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }
}

//    public void findStudent(View v)
//    {
//
//        String studentName =  edit1.getText().toString();
//        Student student = dbHandler.searchStudent(studentName);
//        if(student != null)
//        {
//            edit2.setText(String.valueOf(student.getCourse()));
//            edit3.setText(String.valueOf(student.getSemester()));
//        }
//        else
//        {
//            Toast.makeText(MainActivity.this, "No Match Found !!!", Toast.LENGTH_LONG).show();
//        }
//    }

//    public void deleteStudent(View v)
//    {
//        String studentName =  edit1.getText().toString();
//        boolean result = dbHandler.delete(studentName);
//        if(result)
//        {
//            Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_LONG).show();
//            edit1.setText("");
//            edit2.setText("");
//            edit3.setText("");
//
//        }
//        else
//        {
//            Toast.makeText(MainActivity.this, "Record Not Deleted", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void updateStudent(View v)
//    {
//        String studentName =  edit1.getText().toString();
//        String studentCourse =  edit2.getText().toString();
//        int studentSemester = Integer.parseInt(edit3.getText().toString());
//        dbHandler.update(studentName,studentCourse,studentSemester);
//        Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
//        edit1.setText("");
//        edit2.setText("");
//        edit3.setText("");
//    }
//}
