package com.example.exerciseapp;

import android.os.Parcel;
import android.os.Parcelable;

class Exercise implements Parcelable {
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

    protected Exercise(Parcel in) {
        name = in.readString();
        repetition = in.readString();
        set = in.readString();
        duration = in.readString();
        notes = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(repetition);
        dest.writeString(set);
        dest.writeString(duration);
        dest.writeString(notes);
    }

    public String getName() {return name;}
    public void setName(String name) { this.name = name; }

    public String getRepetition() {return repetition;}
    public void setRepetition(String repetition) { this.repetition = repetition; }


    public String getSet() {return set;}
    public void setSet(String set) { this.set = set; }


    public String getDuration() {return duration;}
    public void setDuration(String duration) { this.duration = duration; }

    public String getNotes() {return notes;}
    public void setNotes(String notes) { this.notes = notes; }

}
