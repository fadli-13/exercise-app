package com.example.exerciseapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExerciseDetailActivity extends AppCompatActivity {

    private TextView exerciseName, sets, duration, repetitions, notesDesc, stopwatchDisplay;
    private ImageView playStopButton;
    private FloatingActionButton fabEdit, fabDelete;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();
    private int position;
    private Exercise exercise;
    private List<Exercise> exerciseList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);

        exerciseName = findViewById(R.id.exerciseName);
        sets = findViewById(R.id.sets);
        duration = findViewById(R.id.duration);
        repetitions = findViewById(R.id.repetitions);
        notesDesc = findViewById(R.id.notes_desc);
        stopwatchDisplay = findViewById(R.id.stopwatchDisplay);
        playStopButton = findViewById(R.id.playStopButton);
        fabEdit = findViewById(R.id.fabEdit);
        fabDelete = findViewById(R.id.fabDelete);

        exercise = getIntent().getParcelableExtra("exercise");
        position = getIntent().getIntExtra("position", -1);

        if (exercise != null) {
            exerciseName.setText(exercise.getName());
            sets.setText(exercise.getSet() + " Sets");
            duration.setText(exercise.getDuration() + " Minutes");
            repetitions.setText(exercise.getRepetition() + " Reps");
            notesDesc.setText(exercise.getNotes());
        }

        playStopButton.setOnClickListener(v -> {
            if (isRunning) {
                stopTimer();
            } else {
                startTimer();
            }
        });

        fabEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(ExerciseDetailActivity.this, EditExerciseActivity.class);
            editIntent.putExtra("exercise", (Parcelable) exercise);
            editIntent.putExtra("position", position);
            startActivityForResult(editIntent, 2);
        });

        fabDelete.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    private void startTimer() {
        isRunning = true;
        playStopButton.setImageResource(android.R.drawable.ic_media_pause);
        handler.postDelayed(timerRunnable, 1000);
    }

    private void stopTimer() {
        isRunning = false;
        playStopButton.setImageResource(android.R.drawable.ic_media_play);
        handler.removeCallbacks(timerRunnable);
    }

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                seconds++;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                stopwatchDisplay.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Exercise")
                .setMessage("Are you sure you want to delete this exercise?")
                .setPositiveButton("Yes", (dialog, which) -> deleteExercise()) // Hapus hanya jika dikonfirmasi
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            exercise = data.getParcelableExtra("edited_exercise");
            position = data.getIntExtra("position", -1);

            if (exercise != null) {
                exerciseName.setText(exercise.getName());
                sets.setText(exercise.getSet() + " Sets");
                duration.setText(exercise.getDuration() + " Minutes");
                repetitions.setText(exercise.getRepetition() + " Reps");
                notesDesc.setText(exercise.getNotes());
            }
            if (position >= 0) {
                Log.d("EXERCISE", "onActivityResult_1: " + position);
                Intent intent = new Intent();
                intent.putExtra("updated_position", position);
                intent.putExtra("updated_exercise", exercise);
                setResult(RESULT_FIRST_USER, intent);
                finish();
            }
        }
    }

    private void deleteExercise() {
        if (position >= 0) {
            Log.d("EXERCISE", "onActivityResult_1: " + position);
            Intent intent = new Intent();
            intent.putExtra("deleted_position", position);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete exercise", Toast.LENGTH_SHORT).show();
        }
    }
}
