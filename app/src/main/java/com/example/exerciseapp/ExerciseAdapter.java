package com.example.exerciseapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList;
    private List<Exercise> fullExerciseList;
    private HomeFragment.OnItemClick listener;

    private static final int REQUEST_CODE_DELETE = 2;

    public ExerciseAdapter(List<Exercise> exerciseList, HomeFragment.OnItemClick listener) {
        this.exerciseList = new ArrayList<>(exerciseList);
        this.fullExerciseList = new ArrayList<>(exerciseList);
        this.listener = listener;
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

        holder.itemView.setOnClickListener(v -> {
            listener.sendData(exercise, position);
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public void addExercise(Exercise newExercise) {
        exerciseList.add(newExercise);
        fullExerciseList.add(newExercise);
        notifyItemInserted(exerciseList.size() - 1);
    }

    public void updateExercise(int position, Exercise updatedExercise) {
        if (position >= 0 && position < exerciseList.size()) {
            exerciseList.set(position, updatedExercise);
            notifyItemChanged(position);
        }
    }

    public void deleteExercise(int position){
        exerciseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, exerciseList.size());
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_OK && data != null) {
            int deletedPosition = data.getIntExtra("deleted_position", -1);
            if (deletedPosition != -1 && deletedPosition < exerciseList.size()) {
                exerciseList.remove(deletedPosition);
                fullExerciseList.remove(deletedPosition);
                notifyItemRemoved(deletedPosition);
            }
        }
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exercise_name_textview);
        }
    }

    public void setFilteredList(List<Exercise> filteredList) {
        exerciseList.clear();
        exerciseList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public void resetList() {
        exerciseList.clear();
        exerciseList.addAll(fullExerciseList);
        notifyDataSetChanged();
    }
}
