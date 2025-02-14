package com.example.exerciseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList;


    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setFilteredList(List<Exercise> filteredList){
        this.exerciseList = filteredList;
        notifyDataSetChanged();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseName;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name_textview);
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.name.setText(exercise.getName());
        holder.repetition.setText("Reps: " + exercise.getRepetition());
        holder.set.setText("Sets: " + exercise.getSet());
        holder.duration.setText("Duration: " + exercise.getDuration());
        holder.notes.setText("Notes: " + exercise.getNotes());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView name, repetition, set, duration, notes;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exercise_name_textview);
            repetition = itemView.findViewById(R.id.repetition_textview);
            set = itemView.findViewById(R.id.set_textview);
            duration = itemView.findViewById(R.id.duration_textview);
            notes = itemView.findViewById(R.id.notes_textview);
        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
//        Exercise exercise = exerciseList.get(position);
//
//        if (exercise != null && exercise.getName() != null) {
//            holder.exerciseName.setText(exercise.getName());
//        } else {
//            holder.exerciseName.setText("No Name");
//        }
//    }

    @Override
    public int getItemCount() {
        return (exerciseList != null) ? exerciseList.size() : 0;
    }
}
