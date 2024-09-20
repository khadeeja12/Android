package com.example.studentapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentUris;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
public class MyContentProvider extends ContentProvider {
    static final String AUTHORITY="com.example.studentapp.provider";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/"+DBHandler.TABLE_NAME);
    //content uri:content://<authority>/<tablename>
    //2 flags
    private static final int STUDENTS=1;
    private static final int STUDENT_ID=2;

    private static final UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
      uriMatcher.addURI(AUTHORITY,DBHandler.TABLE_NAME,STUDENTS);
      uriMatcher.addURI(AUTHORITY,DBHandler.TABLE_NAME,STUDENT_ID);
    }
    private DBHandler dbHandler;
    public MyContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // get MIME type
        int match=uriMatcher.match(uri);
        switch(match)
        {
            case STUDENTS: //vnd means vendor and android platform cursor is dataobject ad entire table is dir
                return "vnd.android.cursor.dir/"+AUTHORITY+"."+DBHandler.TABLE_NAME;
            case STUDENT_ID:
                return "vnd.android.cursor.item/"+AUTHORITY+"."+DBHandler.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown Uri"+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dbHandler.getWritableDatabase();
        long id=db.insert(DBHandler.TABLE_NAME,null,values);
        Uri newUri=ContentUris.withAppendedId(CONTENT_URI,id);
        getContext().getContentResolver().notifyChange(newUri,null);
        return newUri;
    }

    @Override
    public boolean onCreate() {
        dbHandler=new DBHandler(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=dbHandler.getWritableDatabase();
        Cursor cursor;
        int match=uriMatcher.match(uri);

        switch(match)
        {
            case STUDENTS://entire table
                cursor=db.query(DBHandler.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case STUDENT_ID://specific rows givem
                selection=DBHandler.ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=db.query(DBHandler.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI"+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db=dbHandler.getWritableDatabase();
        int rowsUpdated=db.update(DBHandler.TABLE_NAME,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return  rowsUpdated;
    }
}