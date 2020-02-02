package com.saud.app.yaya.Database.exercise;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.saud.app.yaya.Database.DBHelper;
import com.saud.app.yaya.Database.TableColumns;
import com.saud.app.yaya.Model.exercise;
import com.saud.app.yaya.Model.workout;

import java.util.ArrayList;
import java.util.List;

public class DbExercise extends DBHelper implements IExercise<exercise> {


    public DbExercise(@Nullable Context context) {
        super(context);
    }

    @Override
    public List<exercise> fetchExerciseOfworkout(String id) {

        String[] cols={
                TableColumns.exercise.COL_ID,
                TableColumns.exercise.COL_TITLE,
                TableColumns.exercise.COL_WORKOUT_ID,
                TableColumns.exercise.COL_SERIES,
                TableColumns.exercise.COL_REPETITION,
                TableColumns.exercise.COL_RHYTHM,
                TableColumns.exercise.COL_REST_BT_EXERCISE,
                TableColumns.exercise.COL_REST_BT_SERIES
        };
        Cursor cursor= fetchById(TableColumns.exercise.TABLE_NAME,cols,TableColumns.exercise.COL_WORKOUT_ID,id);
        List<exercise> list=new ArrayList<>();
        while (cursor.moveToNext()){
            exercise ex=new exercise();
            ex.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(TableColumns.exercise.COL_ID))));
            ex.setTitle(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_TITLE)));
            ex.setSeries(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_SERIES)));
            ex.setWorkout_id(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_WORKOUT_ID)));
            ex.setRest_bt_exercise(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_REST_BT_EXERCISE)));
            ex.setRest_bt_series(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_REST_BT_SERIES)));
            ex.setRhythm(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_RHYTHM)));
            ex.setRepetition(cursor.getString(cursor.getColumnIndex(TableColumns.exercise.COL_REPETITION)));
            list.add(ex);
        }
        cursor.close();
        return list;

    }

    @Override
    public long save(exercise ex) {
        ContentValues cv=new ContentValues();
        cv.put(TableColumns.exercise.COL_TITLE,ex.getTitle());
        cv.put(TableColumns.exercise.COL_WORKOUT_ID,ex.getWorkout_id());
        cv.put(TableColumns.exercise.COL_SERIES,ex.getSeries());
        cv.put(TableColumns.exercise.COL_REPETITION,ex.getRepetition());
        cv.put(TableColumns.exercise.COL_RHYTHM,ex.getRhythm());
        cv.put(TableColumns.exercise.COL_REST_BT_EXERCISE,ex.getRest_bt_exercise());
        cv.put(TableColumns.exercise.COL_REST_BT_SERIES,ex.getRest_bt_series());
        long id=save(cv,TableColumns.exercise.TABLE_NAME);
        return id;
    }

    @Override
    public boolean update(exercise ex) {
        ContentValues cv=new ContentValues();
        cv.put(TableColumns.exercise.COL_TITLE,ex.getTitle());
        cv.put(TableColumns.exercise.COL_SERIES,ex.getSeries());
        cv.put(TableColumns.exercise.COL_RHYTHM,ex.getRhythm());
        cv.put(TableColumns.exercise.COL_REPETITION,ex.getRepetition());
        cv.put(TableColumns.exercise.COL_REST_BT_SERIES,ex.getRest_bt_series());
        cv.put(TableColumns.exercise.COL_REST_BT_EXERCISE,ex.getRest_bt_exercise());
        update(cv,TableColumns.exercise.TABLE_NAME,TableColumns.exercise.COL_ID,ex.getId());

        return true;
    }

    @Override
    public boolean remove(String id) {
        remove(TableColumns.exercise.TABLE_NAME,TableColumns.exercise.COL_ID,id);
        return true;
    }

    @Override
    public List<exercise> fetchAll() {
        return null;
    }

    @Override
    public exercise fetchById(String id) {
        return null;
    }
}
