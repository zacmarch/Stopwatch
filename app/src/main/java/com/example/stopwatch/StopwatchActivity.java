package com.example.stopwatch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private Stopwatch stopwatch;
    private Handler handler;
    private boolean isRunning;
    private TextView timeText;
    private Button toggle;
    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        toggle = findViewById(R.id.toggle);

        isRunning = false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            stopwatch = new Stopwatch(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            if (running) {
                enableStopwatch();

            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", stopwatch.toString());
        outState.putBoolean("running", isRunning);
    }


    public void buttonClicked(View view) {
        if (isRunning) {
            disableStopwatch();
        }
        else {
            enableStopwatch();
        }
        updateToggleButton();
    }

    private void updateToggleButton() {
        toggle.setText(isRunning ? "Stop" : "Start");
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

    private void enableStopwatch() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopwatch.tick();
                    timeText.setText(stopwatch.toString());
                    handler.postDelayed(this, speed);
                }
            }
        });
    }

    private void disableStopwatch() {
        isRunning = false;
    }


}