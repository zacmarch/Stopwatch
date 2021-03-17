package com.example.stopwatch;
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;

public class Stopwatch {
    private int hours, minutes, seconds;

    Stopwatch() {hours = minutes = seconds = 0;}

    Stopwatch(String timeString) {
        String[] timeParts = timeString.split(":");
        this.hours = Integer.parseInt(timeParts[0]);
        this.minutes = Integer.parseInt(timeParts[1]);
        this.seconds = Integer.parseInt(timeParts[2]);
    }

    void tick(){
        seconds += 1;
        if (seconds > 59){
            seconds = 0;
            minutes += 1;
        }
        if (minutes > 59){
            minutes = 0;
            hours += 1;
        }
        if (hours > 59){
            hours = minutes = seconds = 0;
        }
    }

    @SuppressLint("DefaultLocale")
    @NonNull @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
