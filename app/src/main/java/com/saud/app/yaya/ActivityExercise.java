package com.saud.app.yaya;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saud.app.yaya.Database.exercise.Adapter.ExerciseAdapter;
import com.saud.app.yaya.Database.exercise.DbExercise;
import com.saud.app.yaya.Database.workout.DbWorkout;
import com.saud.app.yaya.Model.exercise;

import java.util.ArrayList;
import java.util.List;

public class ActivityExercise extends AppCompatActivity {

    private String workoutId;
    private String workoutTitle;
    ExerciseAdapter exerciseAdapter;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    Button btn_delWorkout,btn_editWorkout,btn_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        //recyclerView=findViewById(R.id.recyclerView);
        linearLayout=findViewById(R.id.recyclerView);
        btn_delWorkout=findViewById(R.id.btn_delWorkout);
        btn_editWorkout=findViewById(R.id.btn_editWorkout);
        btn_go=findViewById(R.id.btn_go);
        Intent intent=getIntent();
        final DbExercise ex=new DbExercise(getApplicationContext());
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<exercise> ex_list= ex.fetchExerciseOfworkout(workoutId);
                Intent intent1=new Intent(ActivityExercise.this,ActivityTimer.class);
                intent1.putExtra("list",(ArrayList<exercise>)ex_list);
                startActivity(intent1);
            }
        });
        if(intent.getStringExtra("workout_id")!=null)
        {
            workoutId=intent.getStringExtra("workout_id");
            workoutTitle=intent.getStringExtra("workout_title");
            ((TextView)findViewById(R.id.header_text)).setText(workoutTitle);
        }else {
            Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        btn_delWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ActivityExercise.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbExercise dbExercise=new DbExercise(getApplicationContext());
                        DbWorkout dbWorkout=new DbWorkout(getApplicationContext());
                        dbWorkout.remove(workoutId);
                        dbExercise.remove(workoutId);
                        populateLinear();
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
        });
        btn_editWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ActivityExercise.this,ActivityAddExercise.class);
                intent1.putExtra("workout_id",workoutId);
                startActivity(intent1);

            }
        });
        populateLinear();
//        fetchExercise();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateLinear();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        populateLinear();
    }
    private void populateLinear(){

        TextView title,series, rhythm,rest_exercise,rest_series,repetition;
        ImageView editExercise;
        DbExercise ex=new DbExercise(getApplicationContext());
        List<exercise> ex_list= ex.fetchExerciseOfworkout(workoutId);
        if(ex_list.size()==0){
            ((LinearLayout) linearLayout).removeAllViews();
            View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.no_exercise,null,false);
                linearLayout.addView(view);
                return;
        }
        if(((LinearLayout) linearLayout).getChildCount() > 0){
            ((LinearLayout) linearLayout).removeAllViews();
        }
        for (final exercise ex_item: ex_list) {
            View view= LayoutInflater
                    .from(getApplicationContext())
                    .inflate(R.layout.exercise_layout,
                            null,
                            false);
            editExercise=view.findViewById(R.id.editExercise);
            title=view.findViewById(R.id.title);
            series=view.findViewById(R.id.series);
            rhythm=view.findViewById(R.id.Rhythm);
            rest_series=view.findViewById(R.id.rest_series);
            rest_exercise=view.findViewById(R.id.rest_exercise);
            repetition=view.findViewById(R.id.repetition);
            title.setText(ex_item.getTitle());
            series.setText(ex_item.getSeries());
            rhythm.setText(ex_item.getRhythm());
            rest_exercise.setText(ex_item.getRest_bt_exercise());
            rest_series.setText(ex_item.getSeries());
            repetition.setText(ex_item.getRepetition());
            editExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(ActivityExercise.this,ActivityAddExercise.class);
                    intent1.putExtra("exerciseObj",ex_item);
                    startActivity(intent1);
                }
            });
            linearLayout.addView(view);
        }
    }
    private void fetchExercise() {
        DbExercise ex=new DbExercise(getApplicationContext());
        List<exercise> ex_list= ex.fetchExerciseOfworkout(workoutId);
        exerciseAdapter=new ExerciseAdapter(this,ex_list);
        exerciseAdapter.setOnEditExerciseListner(new ExerciseAdapter.OnEditExerciseListner() {
            @Override
            public void onClickListner(exercise ex) {
                Intent intent1=new Intent(ActivityExercise.this,ActivityAddExercise.class);
                intent1.putExtra("exerciseObj",ex);
                startActivity(intent1);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(exerciseAdapter);
    }

}
