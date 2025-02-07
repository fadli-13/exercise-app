package com.example.exerciseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExerciseDetailActivity extends AppCompatActivity {
    private FloatingActionButton fabEdit, fabDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);

//        // Intent untuk berpindah ke StopwatchActivity
//        Intent intent = new Intent(ExerciseDetailActivity.this, StopwatchActivity.class);
//        startActivity(intent);
//
//        fabEdit = findViewById(R.id.fabEdit);
//        fabDelete = findViewById(R.id.fabDelete);
//
//        // Edit Button click listener
//        fabEdit.setOnClickListener(view -> {
//            // Start EditExerciseActivity when edit button is clicked
//            Intent intent = new Intent(ExerciseDetailActivity.this, EditExerciseActivity.class);
//            startActivity(intent);
//        });
//
//        // Delete Button click listener
//        fabDelete.setOnClickListener(view -> {
//            // Remove data from list or database
//            // For this example, show a Toast as a placeholder for delete logic
//            Toast.makeText(ExerciseDetailActivity.this, "Exercise Deleted", Toast.LENGTH_SHORT).show();
//
//            // Optionally: Close the activity after deleting
//            finish();
//        });


    }
}
