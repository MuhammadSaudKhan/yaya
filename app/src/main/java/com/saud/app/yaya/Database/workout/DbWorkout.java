package com.saud.app.yaya.Database.workout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.saud.app.yaya.Database.DBHelper;
import com.saud.app.yaya.Database.TableColumns;
import com.saud.app.yaya.Database.exercise.DbExercise;
import com.saud.app.yaya.Model.workout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbWorkout extends DBHelper implements IWorkout<workout> {
    public DbWorkout(@Nullable Context context) {
        super(context);
    }

    @Override
    public workout fetchExerciseOfworkout(String id) {
        return null;
    }

    @Override
    public long save(workout workout) {
        ContentValues cv=new ContentValues();
        cv.put(TableColumns.workout.COL_TITLE,workout.getTitle());
        long id=save(cv,TableColumns.workout.TABLE_NAME);

        return id;
    }

    @Override
    public boolean update(workout workout) {
        ContentValues cv=new ContentValues();
        cv.put(TableColumns.workout.COL_TITLE,workout.getTitle());
        if(update(cv,TableColumns.workout.TABLE_NAME,TableColumns.workout.COL_ID,workout.getId())){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String id) {

        remove(TableColumns.workout.TABLE_NAME,TableColumns.workout.COL_ID,id);

        return true;
    }

    @Override
    public List<workout> fetchAll() {
        String[] cols={TableColumns.workout.COL_ID,TableColumns.workout.COL_TITLE};
        Cursor cursor= fetchAll(TableColumns.workout.TABLE_NAME,cols);
        List<workout> list=new ArrayList<>();
        while (cursor.moveToNext()){
            workout work=new workout();
            work.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(TableColumns.workout.COL_ID))));
            work.setTitle(cursor.getString(cursor.getColumnIndex(TableColumns.workout.COL_TITLE)));
            list.add(work);
        }
        cursor.close();
        return list;
    }

    @Override
    public workout fetchById(String id) {
        String[] cols={TableColumns.workout.COL_ID,TableColumns.workout.COL_TITLE};

        Cursor cursor=fetchById(TableColumns.workout.TABLE_NAME,cols,TableColumns.workout.COL_ID,id);
        workout work=new workout();
        while (cursor.moveToNext()){
            work.setId(cursor.getString(cursor.getColumnIndex(TableColumns.workout.COL_ID)));
            work.setTitle(cursor.getString(cursor.getColumnIndex(TableColumns.workout.COL_TITLE)));
        }
        return work;
    }
}
