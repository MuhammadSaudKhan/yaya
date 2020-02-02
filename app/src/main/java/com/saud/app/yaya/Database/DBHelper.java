package com.saud.app.yaya.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="fitness.db";
    public static final int DATABASE_VERSION=1;
    public DBHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create workout
        Map<String,String> map=new HashMap<>();
        map.put(TableColumns.workout.COL_ID,"INTEGER PRIMARY KEY AUTOINCREMENT");
        map.put(TableColumns.workout.COL_TITLE,"TEXT");
        String queryWorkout= QueryHelper.createTable(TableColumns.workout.TABLE_NAME,map);
        Log.d("QueryFunction",queryWorkout);
        db.execSQL(queryWorkout);
        //create exercise table
        Map<String,String> map1=new HashMap<>();
        map1.put(TableColumns.exercise.COL_ID,"INTEGER PRIMARY KEY AUTOINCREMENT");
        map1.put(TableColumns.exercise.COL_TITLE,"TEXT");
        map1.put(TableColumns.exercise.COL_REPETITION,"TEXT");
        map1.put(TableColumns.exercise.COL_RHYTHM,"TEXT");
        map1.put(TableColumns.exercise.COL_SERIES,"TEXT");
        map1.put(TableColumns.exercise.COL_REST_BT_SERIES,"TEXT");
        map1.put(TableColumns.exercise.COL_REST_BT_EXERCISE,"TEXT");
        map1.put(TableColumns.exercise.COL_WORKOUT_ID,"INTEGER(11)");
        String queryExercise= QueryHelper.createTable(TableColumns.exercise.TABLE_NAME,map1);
        Log.d("QueryFunction",queryExercise);
        db.execSQL(queryExercise);

    }
    public long save(ContentValues cv,String tableName){
        SQLiteDatabase db=this.getWritableDatabase();
        long chck=db.insert(tableName,null,cv);
        if (chck==-1){
            db.close();
            return chck;

        }
        db.close();
        return chck;
    }

    public boolean update(ContentValues cv,String tableName,String col,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long chck=db.update(tableName,cv,col+"=?",new String[]{id});
        if (chck==-1){
            db.close();
            return false;

        }
        db.close();
        return true;
    }
    public boolean remove(String table,String col,String id){
        SQLiteDatabase database=this.getWritableDatabase();
        int chk=database.delete(table,col+"=?",new String[]{id});
        if (chk==-1){
            return false;
        }
        return true;
    }
    public Cursor fetchAll(String table,String[] column){
        SQLiteDatabase database=this.getReadableDatabase();

        return database.query(
                table,
                column,
                null,
                null,
                null,
                null,
                null
                );
    }
    public Cursor fetchById(String table,String[] columns,String col,String id){
        SQLiteDatabase database=this.getReadableDatabase();
        StringBuffer buffer=new StringBuffer();
        int i=0;
        for (String colItem: columns) {
            if(columns.length-1!=i)
                buffer.append(colItem+",");
            else
                buffer.append(colItem);
            i++;
        }
        return database.rawQuery("select "+buffer.toString()+" from "+table+" where "+col+"=?",new String[]{id});
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
