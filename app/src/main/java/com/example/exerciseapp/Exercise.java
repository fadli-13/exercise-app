package com.example.exerciseapp;

public class Exercise {
    private String name;
    private String repetition;
    private String set;
    private String duration;
    private String notes;

    public Exercise(String name, String repetition, String set, String duration, String notes) {
        this.name = name;
        this.repetition = repetition;
        this.set = set;
        this.duration = duration;
        this.notes = notes;
    }

    public String getName() { return name; }
    public String getRepetition() { return repetition; }
    public String getSet() { return set; }
    public String getDuration() { return duration; }
    public String getNotes() { return notes; }
}
