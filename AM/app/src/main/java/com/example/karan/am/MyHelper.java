package com.example.karan.am;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
class MyHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WM_database.db";
    public static final String TABLE_NAME1 = "Account";
    public static final String TABLE_NAME2 = "Login";
    public static final String TABLE_NAME3 = "Stock";
    public static final String COL_1 = "USERID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "PHONE";
    public static final String COL_5 = "EMAIL";
    public static final String COL_6 = "USERTYPE";
    public static final String COL_7 = "PASSWORD";
    public static final String COL_8 = "QUANTITY";
    public static final String COL_9 = "WASTETYPE";
    public static final String COL_10 = "COST";
    public MyHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME1+"(USERID Text ,NAME Text ,ADDRESS Text ,PHONE Text ,EMAIL Text,USERTYPE Text )");
        db.execSQL("create table "+TABLE_NAME2+"(USERID Text ,PASSWORD Text ,USERTYPE Text )");
        db.execSQL("create table "+TABLE_NAME3+"(USERID Text ,WASTETYPE Text ,QUANTITY Text ,COST Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
    }

    public boolean insertData(String username,String name,String address,String phone,String email,String usertype,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,phone);
        contentValues.put(COL_5,email);
        contentValues.put(COL_6,usertype);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(COL_1,username);
        contentValues1.put(COL_7,password);
        contentValues1.put(COL_6,usertype);
        db.insert(TABLE_NAME2,null,contentValues1);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME1, null);
        return res;
    }
    public String SignIn(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select "+COL_6+" from "+TABLE_NAME2+" where "+COL_1+"=? AND "+COL_7+"=?",new String[]{username,password});
        if(res.getCount()>0)
        {
            String usertype;
            res.moveToFirst();
                usertype=res.getString(0);

            return usertype;
        }
        else
        {
            return "FFFF";
        }
    }

 public boolean Sell(String username,String wastetype,String quantity,String cost)
 {
     SQLiteDatabase db=this.getWritableDatabase();
     ContentValues contentValues=new ContentValues();
     contentValues.put(COL_1,username);
     contentValues.put(COL_9,wastetype);
     contentValues.put(COL_8,quantity);
     contentValues.put(COL_10,cost);
     long res=db.insert(TABLE_NAME3,null,contentValues);
     if(res==-1)
     {
         return false;
     }
     else{
         return true;
     }

 }

 public Cursor fetchData(String wastetype)
 {
     SQLiteDatabase db=this.getReadableDatabase();
     Cursor cursor;
     if(wastetype==null||wastetype=="all")
     {
         cursor=db.rawQuery("select * from "+TABLE_NAME3,null);
     }
     else
     {
         cursor=db.rawQuery("select * from "+TABLE_NAME3+" where "+COL_9+"=?",new String[]{wastetype});
     }
     return cursor;
 }
 public Cursor getDetail(String user)
 {
     SQLiteDatabase db=this.getReadableDatabase();
     Cursor res=db.rawQuery("select * from "+TABLE_NAME1+" where "+COL_1+"=?",new String[]{user});
     return res;
 }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "ID = ?", new String[]{id});
    }


public boolean buy(String user,String wastetype,String Quantity,String cost)
{
    SQLiteDatabase db=getWritableDatabase();
    long res=db.delete(TABLE_NAME3,COL_1+"=? AND "+COL_9+"=? AND "+COL_8+"=? AND "+COL_10+"+?",new String[]{user,wastetype,Quantity,cost});
    if(res!=-1)
        return true;
    else
        return false;
}
}