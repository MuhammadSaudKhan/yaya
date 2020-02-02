package com.saud.app.yaya.Database.exercise.Adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saud.app.yaya.Model.exercise;
import com.saud.app.yaya.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.viewHolder> {
    List<exercise> exercises;
    OnEditExerciseListner listner;

    public ExerciseAdapter(Activity activity,List<exercise> exercises) {

        this.exercises = exercises;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_layout,null,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {

        holder.title.setText(exercises.get(position).getTitle());
        holder.rhythm.setText(exercises.get(position).getRhythm());
        holder.series.setText(exercises.get(position).getSeries());
        holder.repetition.setText(exercises.get(position).getRepetition());
        holder.rest_exercise.setText(exercises.get(position).getRest_bt_exercise());
        holder.rest_series.setText(exercises.get(position).getRest_bt_series());
        holder.editExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClickListner(exercises.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView title,series, rhythm,rest_exercise,rest_series,repetition;
        ImageView editExercise;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            editExercise=itemView.findViewById(R.id.editExercise);
            title=itemView.findViewById(R.id.title);
            series=itemView.findViewById(R.id.series);
            rhythm=itemView.findViewById(R.id.Rhythm);
            rest_series=itemView.findViewById(R.id.rest_series);
            rest_exercise=itemView.findViewById(R.id.rest_exercise);
            repetition=itemView.findViewById(R.id.repetition);
        }
    }
    public interface OnEditExerciseListner{
        void onClickListner(exercise ex);
    }
    public void setOnEditExerciseListner(OnEditExerciseListner listner){
        this.listner=listner;
    }
}
