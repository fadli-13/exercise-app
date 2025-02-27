package com.example.exerciseapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;
    private static final int REQUEST_CODE_ADD = 100;
    private static final int REQUEST_CODE_EDIT = 2;
    private static final int REQUEST_CODE_DELETE = 1;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadExerciseData();

        if (exerciseList == null) {
            exerciseList = new ArrayList<>();
        }

        exerciseAdapter = new ExerciseAdapter(exerciseList, listener);
        recyclerView.setAdapter(exerciseAdapter);

        FloatingActionButton fabAddExercise = view.findViewById(R.id.fab_add_exercise);
        fabAddExercise.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExerciseActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        });
    }

    public interface OnItemClick {
        void sendData(Exercise exercise, int position);
    }

    public OnItemClick listener = new OnItemClick() {
        @Override
        public void sendData(Exercise exercise, int position) {
            Intent intent = new Intent(getContext(), ExerciseDetailActivity.class);
            intent.putExtra("exercise", exercise);
            intent.putExtra("position", position);
            startActivityForResult(intent, REQUEST_CODE_DELETE);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("EXERCISE", "ReqCode: " + requestCode + ", ResultCode: " + resultCode);

        if (requestCode == REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK && data != null) {
            Exercise newExercise = data.getParcelableExtra("exercise");
            if (newExercise != null) {
                exerciseList.add(newExercise);
                exerciseAdapter.addExercise(newExercise);
                saveExerciseData();
                Log.d("EXERCISE", "Exercise added: " + newExercise.getName());
            }
        }

        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_FIRST_USER && data != null) {
            Exercise editedExercise = data.getParcelableExtra("updated_exercise");
            int position = data.getIntExtra("updated_position", -1);

            if (editedExercise != null && position >= 0 && position < exerciseList.size()) {
                exerciseList.set(position, editedExercise);
                exerciseAdapter.updateExercise(position, editedExercise);
                saveExerciseData();
                Log.d("EXERCISE", "Exercise updated at position: " + position);
            } else {
                Log.e("EXERCISE", "Failed to update exercise");
            }
        }

        if (requestCode == REQUEST_CODE_DELETE && resultCode == Activity.RESULT_OK && data != null) {
            int deletedPosition = data.getIntExtra("deleted_position", -1);
            Log.d("EXERCISE", "Deleted Position: " + deletedPosition);

            if (deletedPosition != -1) {
                exerciseAdapter.deleteExercise(deletedPosition);
                exerciseList.remove(deletedPosition);
                saveExerciseData();
            } else {
                Log.e("EXERCISE", "Failed to delete exercise");
            }
        }
    }

    private void filterList(String text) {
        List<Exercise> filteredList = new ArrayList<>();
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(exercise);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            exerciseAdapter.setFilteredList(filteredList);
        }
    }

    private void saveExerciseData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("exercise_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(exerciseList);
        editor.putString("exercise_list", json);
        editor.apply();
    }

    private void loadExerciseData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("exercise_prefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("exercise_list", null);
        Type type = new TypeToken<ArrayList<Exercise>>() {}.getType();
        exerciseList = json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }
}
