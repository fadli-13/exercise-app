package com.example.exerciseapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StopwatchActivity extends AppCompatActivity {

    private TextView stopwatchDisplay;
    private ImageView playStopButton;
    private boolean isRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                seconds++;
                updateStopwatch();
                handler.postDelayed(this, 1000); // update every second
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_detail);

        stopwatchDisplay = findViewById(R.id.stopwatchDisplay);
        playStopButton = findViewById(R.id.playStopButton);

        playStopButton.setOnClickListener(v -> {
            if (isRunning) {
                // Stop stopwatch
                isRunning = false;
                playStopButton.setImageResource(android.R.drawable.ic_media_play); // Change to play icon
            } else {
                // Start stopwatch
                isRunning = true;
                playStopButton.setImageResource(android.R.drawable.ic_media_pause); // Change to stop icon
                handler.post(runnable); // Start the timer
            }
        });
    }

    // Update the stopwatch display
    private void updateStopwatch() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        String time = String.format("%02d:%02d", minutes, remainingSeconds);
        stopwatchDisplay.setText(time);
    }
}
