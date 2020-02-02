package com.saud.app.yaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.saud.app.yaya.Database.workout.Adapter.WorkoutAdapter;
import com.saud.app.yaya.Database.workout.DbWorkout;
import com.saud.app.yaya.Model.workout;

import java.util.List;

public class ActivityWorkout extends AppCompatActivity {
    TextView header_text;
    Button btnAddWorkout;
    RecyclerView recyclerView;
    WorkoutAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ((TextView)findViewById(R.id.header_text)).setText("WORKOUT");
        intUI();
        btnAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityWorkout.this,ActivityAddWorkout.class));
            }
        });

        fetchWorkout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchWorkout();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fetchWorkout();
    }

    private void fetchWorkout() {
        DbWorkout dbWorkout=new DbWorkout(getApplicationContext());
        List<workout> list= dbWorkout.fetchAll();
        adapter=new WorkoutAdapter(this,list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListner(new WorkoutAdapter.onItemClickListner() {
            @Override
            public void onClickListner(String id,String title) {
                Intent intent=new Intent(ActivityWorkout.this,ActivityExercise.class);
                intent.putExtra("workout_id",id);
                intent.putExtra("workout_title",title);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void intUI() {
        recyclerView=findViewById(R.id.recyclerView);
        btnAddWorkout=findViewById(R.id.btnAddWorkout);
    }
}
