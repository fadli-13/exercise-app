package com.example.exerciseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList;

    public void setFilteredList(List<Exercise> filteredList){
        this.exerciseList = filteredList;
        notifyDataSetChanged();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextInputEditText exerciseNameEditText;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseNameEditText = itemView.findViewById(R.id.exercise_name_edittext);
        }
    }

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
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

        if (exercise != null && exercise.getName() != null) {
            holder.exerciseNameEditText.setText(exercise.getName());
        } else {
            holder.exerciseNameEditText.setText("No Name"); // Default jika null
        }
    }

    @Override
    public int getItemCount() {
        return (exerciseList != null) ? exerciseList.size() : 0;
    }
}
