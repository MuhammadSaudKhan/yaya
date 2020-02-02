package com.saud.app.yaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saud.app.yaya.Database.exercise.DbExercise;
import com.saud.app.yaya.Database.workout.DbWorkout;
import com.saud.app.yaya.Model.exercise;
import com.saud.app.yaya.Model.workout;

public class ActivityAddExercise extends AppCompatActivity {
    private static final String TAG = "ActivityAddExercise";
    String workout_title;
    String workout_id;
    EditText exerciseTitle,series,repetition,Rhythm,rest_series,rest_exercise;
    Button btnCreate,btnCancel;
    exercise ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        Intent intent=getIntent();
        ex=(exercise) intent.getSerializableExtra("exerciseObj");

        if(intent.getStringExtra("workout_title")!=null){
            workout_title=intent.getStringExtra("workout_title");
        }
        if(intent.getStringExtra("workout_id")!=null){
            workout_id=intent.getStringExtra("workout_id");
        }
        //initialize
        initUI();
        if(ex!=null){
            btnCreate.setText("Update");
            setData(ex);
            Toast.makeText(this, ex.getId()+"", Toast.LENGTH_SHORT).show();
        }
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addExercise() {
        Log.d(TAG,"addExercise:  saving exercise");
        exercise ex_model=new exercise();
        ex_model.setTitle(exerciseTitle.getText().toString().trim());
        ex_model.setSeries(series.getText().toString().trim());
        ex_model.setRepetition(repetition.getText().toString().trim());
        ex_model.setRhythm(Rhythm.getText().toString().trim());
        ex_model.setRest_bt_series(rest_series.getText().toString().trim());
        ex_model.setRest_bt_exercise(rest_exercise.getText().toString().trim());

        if(ex_model.getTitle().isEmpty()){
            exerciseTitle.setError("Enter title");
            exerciseTitle.requestFocus();
            return;
        }

        if(ex_model.getSeries().isEmpty()){
            series.setError("Enter number of series");
            series.requestFocus();
            return;
        }

        if(ex_model.getRepetition().isEmpty()){
            repetition.setError("Enter number of repetition");
            repetition.requestFocus();
            return;
        }

        if(ex_model.getRest_bt_series().isEmpty()){
            rest_series.setError("Enter rest between series");
            rest_series.requestFocus();
            return;
        }

        if(ex_model.getRest_bt_exercise().isEmpty()){
            rest_exercise.setError("Enter rest between exercise");
            rest_exercise.requestFocus();
            return;
        }
        if(workout_id!=null){
            DbExercise dbExercise=new DbExercise(getApplicationContext());
            ex_model.setWorkout_id(workout_id);
            long ex_lastId=dbExercise.save(ex_model);
            if(ex_lastId==-1)
            {
                Toast.makeText(this, "Exercise is not added", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Exercise is add to "+workout_title, Toast.LENGTH_SHORT).show();
            restData();
            return;
        }
        if(ex!=null){
            DbExercise dbExercise=new DbExercise(getApplicationContext());
            ex_model.setId(ex.getId());
            dbExercise.update(ex_model);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            return;
        }
        DbWorkout dbWorkout=new DbWorkout(getApplicationContext());
        workout w=new workout();
        w.setTitle(workout_title);
        long lastId=dbWorkout.save(w);
        if(lastId==-1){
            Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        String workoutId=String.valueOf(lastId);
        ex_model.setWorkout_id(workoutId);
        DbExercise dbExercise=new DbExercise(getApplicationContext());
        long ex_lastId=dbExercise.save(ex_model);
        if(ex_lastId==-1){
            Toast.makeText(this, "Exercise is not added", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Exercise is add to "+workout_title, Toast.LENGTH_SHORT).show();
        restData();
    }
    private void setData(exercise ex) {
        exerciseTitle.setText(ex.getTitle());
        Rhythm.setText(ex.getRhythm());
        rest_exercise.setText(ex.getRest_bt_exercise());
        rest_series.setText(ex.getRest_bt_series());
        series.setText(ex.getSeries());
        repetition.setText(ex.getRepetition());
    }
    private void restData() {
        exerciseTitle.setText("");
        Rhythm.setText("");
        rest_exercise.setText("");
        rest_series.setText("");
        series.setText("");
        repetition.setText("");
    }

    private void initUI() {
        Log.e(TAG,"initUI: initialize ui");
        exerciseTitle=findViewById(R.id.exerciseTitle);
        series=findViewById(R.id.series);
        repetition=findViewById(R.id.repetition);
        Rhythm=findViewById(R.id.Rhythm);
        rest_exercise=findViewById(R.id.rest_exercise);
        rest_series=findViewById(R.id.rest_series);
        btnCreate=findViewById(R.id.btnCreate);
        btnCancel=findViewById(R.id.btnCancel);
    }
}
