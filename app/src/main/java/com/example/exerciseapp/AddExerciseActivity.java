package com.example.exerciseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AddExerciseActivity extends AppCompatActivity {
    private EditText nameField, repetitionField, setField, durationField, notesField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

        nameField = findViewById(R.id.name_field);
        repetitionField = findViewById(R.id.repetition_field);
        setField = findViewById(R.id.set_field);
        durationField = findViewById(R.id.duration_field);
        notesField = findViewById(R.id.notes_field);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    String name = nameField.getText().toString();
                    String repetition = repetitionField.getText().toString();
                    String set = setField.getText().toString();
                    String duration = durationField.getText().toString();
                    String notes = notesField.getText().toString();

                    Exercise newExercise = new Exercise(name, repetition, set, duration, notes);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("exercise", newExercise);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (nameField.getText().toString().isEmpty() ||
                repetitionField.getText().toString().isEmpty() ||
                setField.getText().toString().isEmpty() ||
                durationField.getText().toString().isEmpty()) {

            Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
