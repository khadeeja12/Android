package com.example.studentdb;
//import the classes for sqlite database
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
public class DBHandler extends SQLiteOpenHelper{
    //create constant variables
    //db name
    private static final String DB_NAME="student_db";
    private static final int DB_VERSION=1; //we first create the database therefore we given 1 , later we can upgrade and make it as 2 or 3
    private static final String TABLE_NAME="student_details";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String COURSE="course";
    private static final String SEMESTER="semester";
    //create constructor
    public DBHandler(Context context){
        //arguments:context object,db name,
        //null,db version
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           String query= "CREATE TABLE "+TABLE_NAME+"("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +NAME+ " TEXT,"+COURSE+ " TEXT,"+SEMESTER+ " INTEGER)";
    //execute above query
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { // i is old version or  i1 is new version
       //check if the table already exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addNewStudent(String name,String course,int semester){
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();
       //pass data as key-value pairs, column-data
        values.put(NAME,name);
        values.put(COURSE,course);
        values.put(SEMESTER,semester);
        db.insert(TABLE_NAME,null,values);
       // db.close();
    }
}
