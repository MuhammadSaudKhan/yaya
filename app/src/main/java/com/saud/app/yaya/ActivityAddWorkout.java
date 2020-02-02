package com.saud.app.yaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saud.app.yaya.Database.workout.DbWorkout;
import com.saud.app.yaya.Model.workout;

public class ActivityAddWorkout extends AppCompatActivity {
    Button btnCreate,btnCancel,btnStartWorkout;
    EditText workoutTitle;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
        intiUi();
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workout w=new workout();
                w.setTitle(workoutTitle.getText().toString().trim());
                if (w.getTitle().isEmpty()){
                    workoutTitle.setError("Enter workout");
                    workoutTitle.requestFocus();
                    return;
                }
                if(w.getTitle().length()<3){
                    workoutTitle.setError("workout name must contains 3 characters");
                    workoutTitle.requestFocus();
                    return;
                }
                Intent intent=new Intent(ActivityAddWorkout.this,ActivityAddExercise.class);
                intent.putExtra("workout_title",w.getTitle());
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void intiUi() {
        btnCancel=findViewById(R.id.btnCancel);
        btnCreate=findViewById(R.id.btnCreate);
        workoutTitle=findViewById(R.id.workoutTitle);
    }
}
