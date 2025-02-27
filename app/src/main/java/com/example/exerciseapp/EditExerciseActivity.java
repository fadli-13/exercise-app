package com.example.exerciseapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditExerciseActivity extends AppCompatActivity {

    private EditText editName, editSets, editDuration, editReps, editNotes;
    private Button btnSave;
    private Exercise exercise;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise);

        editName = findViewById(R.id.editName);
        editSets = findViewById(R.id.editSets);
        editDuration = findViewById(R.id.editDuration);
        editReps = findViewById(R.id.editRepetition);
        editNotes = findViewById(R.id.editNotes);
        btnSave = findViewById(R.id.btnSave);

        exercise = getIntent().getParcelableExtra("exercise");
        position = getIntent().getIntExtra("position", -1);

        if (exercise != null) {
            editName.setText(exercise.getName());
            editSets.setText(String.valueOf(exercise.getSet()));
            editDuration.setText(String.valueOf(exercise.getDuration()));
            editReps.setText(String.valueOf(exercise.getRepetition()));
            editNotes.setText(exercise.getNotes());
        }

        btnSave.setOnClickListener(v -> updateExercise());
    }

    private void updateExercise() {
        if (exercise != null) {
            exercise.setName(editName.getText().toString());
            exercise.setSet((editSets.getText().toString()));
            exercise.setDuration((editDuration.getText().toString()));
            exercise.setRepetition((editReps.getText().toString()));
            exercise.setNotes(editNotes.getText().toString());

            Log.d("EXERCISE", "Saving: " + exercise.getName() + ", Pos: " + position);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("edited_exercise", exercise);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Log.e("EXERCISE", "Failed to save changes");
            Toast.makeText(this, "Failed to save changes", Toast.LENGTH_SHORT).show();
        }
    }

}
