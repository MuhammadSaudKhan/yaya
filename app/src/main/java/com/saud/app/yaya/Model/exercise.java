package com.saud.app.yaya.Model;

import java.io.Serializable;

public class exercise implements Serializable {
    private String id;
    private String workout_id;
    private String title;
    private String series;
    private String repetition;
    private String rhythm;
    private String rest_bt_series;
    private String rest_bt_exercise;

    public exercise() {
    }

    public exercise(String id, String workout_id, String title, String series,
                    String repetition, String rhythm, String rest_bt_series,
                    String rest_bt_exercise) {
        this.id = id;
        this.workout_id = workout_id;
        this.title = title;
        this.series = series;
        this.repetition = repetition;
        this.rhythm = rhythm;
        this.rest_bt_series = rest_bt_series;
        this.rest_bt_exercise = rest_bt_exercise;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(String workout_id) {
        this.workout_id = workout_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public String getRhythm() {
        return rhythm;
    }

    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

    public String getRest_bt_series() {
        return rest_bt_series;
    }

    public void setRest_bt_series(String rest_bt_series) {
        this.rest_bt_series = rest_bt_series;
    }

    public String getRest_bt_exercise() {
        return rest_bt_exercise;
    }

    public void setRest_bt_exercise(String rest_bt_exercise) {
        this.rest_bt_exercise = rest_bt_exercise;
    }
}
