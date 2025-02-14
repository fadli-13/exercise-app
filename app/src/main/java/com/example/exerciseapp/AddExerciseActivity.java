import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import com.example.exerciseapp.Exercise;
import com.example.exerciseapp.ExerciseAdapter;

public class AddExerciseActivity extends AppCompatActivity {
    private ExerciseAdapter adapter;
    private ArrayList<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        exerciseList = new ArrayList<>();
        adapter = new ExerciseAdapter(exerciseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        EditText nameField = findViewById(R.id.name_field);
        EditText repetitionField = findViewById(R.id.repetition_field);
        EditText setField = findViewById(R.id.set_field);
        EditText durationField = findViewById(R.id.duration_field);
        EditText notesField = findViewById(R.id.notes_field);
        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String repetition = repetitionField.getText().toString();
                String set = setField.getText().toString();
                String duration = durationField.getText().toString();
                String notes = notesField.getText().toString();

                Exercise newExercise = new Exercise(name, repetition, set, duration, notes);
                adapter.addExercise(newExercise);
            }
        });
    }
}
