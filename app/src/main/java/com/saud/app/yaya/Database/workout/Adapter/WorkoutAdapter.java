package com.saud.app.yaya.Database.workout.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saud.app.yaya.Database.exercise.DbExercise;
import com.saud.app.yaya.Model.exercise;
import com.saud.app.yaya.Model.workout;
import com.saud.app.yaya.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.viewHolder> {
    Activity activity;
    List<workout> list;
    onItemClickListner listner;
    public WorkoutAdapter(Activity activity, java.util.List<workout> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_box,null,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        String title=list.get(position).getTitle();
        holder.workout_title.setText(title);

        DbExercise ex=new DbExercise(activity.getApplicationContext());
        final String workoutId=list.get(position).getId();
        List<exercise>ex_list= ex.fetchExerciseOfworkout(workoutId);
        StringBuilder builder=new StringBuilder();
        int i=0;
        for (exercise ex_item:ex_list) {
            if (ex_list.size()-1==i)
                builder.append(ex_item.getTitle());
            else
                builder.append(ex_item.getTitle()).append("-");

            i++;
        }
        holder.exercises.setText(builder.toString());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClickListner(workoutId,list.get(position).getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView workout_title,exercises;
        RelativeLayout relativeLayout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView;
            workout_title=itemView.findViewById(R.id.workout_day);
            exercises=itemView.findViewById(R.id.workout_exercise);

        }
    }
    public interface onItemClickListner{
       void onClickListner(String id,String title);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        this.listner=listner;
    }
}
