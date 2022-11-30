package com.example.karan.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class helper extends SQLiteOpenHelper {
    public helper(Context context) {
        super(context,"Base", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Table1(Name Text,Pass Text,UserType Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table Table1");
        onCreate(db);
    }
    public boolean insertData(String username,String password,String usertype)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",username);
        contentValues.put("Pass",password);
        contentValues.put("UserType",usertype);
        long res=db.insert("Table1",null,contentValues);
        if(res==-1)
            return false;
        else
            return true;

    }
    public String Login(String username, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query("Table1", new String[]{"UserType"},"select UserType from Table1 where Name=? AND Pass=?",new String[]{username,password},null,null,null);
        if(cursor.getCount()==1)
        {
            return cursor.getString(cursor.getColumnIndex("UserType"));
        }
        else
            return "FFFF";
    }
}
