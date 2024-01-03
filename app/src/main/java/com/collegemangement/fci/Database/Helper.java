package com.collegemangement.fci.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.collegemangement.fci.ClassesModel.makeorder;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {


    public static final String Databasename = "smalldb.db";
    public static final int version = 1;

    public Helper(@Nullable Context context) {
        super(context, Databasename, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        final String CreateTable = "CREATE TABLE " + Contract.WiatlistEntity.TableName + "("+
//                Contract.WiatlistEntity._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                Contract.WiatlistEntity.Columnnem + "TEXT NOT NULL," +
//                Contract.WiatlistEntity.Columnsalary + "TEXT NOT NULL," +
//                Contract.WiatlistEntity.Time + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + "); ";

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS k ( id INTEGER PRIMARY KEY , name TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS type ( id INTEGER PRIMARY KEY , type TEXT )");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS k");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS type");



        onCreate(sqLiteDatabase);

    }

    public void inserttype (String x) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", x);
        sqLiteDatabase.insert("type", null, contentValues);
    }


    public String gettype() {
//        String x = null;
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from type ", null);
//        cursor.moveToFirst();
//        x = cursor.getString(cursor.getColumnIndex("type"));



        String x = null;
        ArrayList<makeorder> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from type ", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            x = cursor.getString(cursor.getColumnIndex("type"));
            cursor.moveToNext();
        }

        return x;


    }


    public void insert(String x) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", x);
        sqLiteDatabase.insert("k", null, contentValues);
    }

    public String getAlldata() {
        String x = null; 
        ArrayList<makeorder> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from k ", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
//
             x = cursor.getString(cursor.getColumnIndex("name"));
//            String y = cursor.getString(cursor.getColumnIndex("salary"));
//
//
//            Log.e("db" , x);
//            Log.e("dbdb" , y);
//
//            arrayList.add(new makeorder(x , y));
//
            cursor.moveToNext();

//        String x = cursor.getString(cursor.getColumnIndex("name"));


            
        }

        return x;
    }



    public void delete (Long id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("delete from k where id ="+id);
        Log.e("Delete" , id.toString()) ;


    }


}
