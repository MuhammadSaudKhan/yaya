package com.saud.app.yaya.Database.exercise;

import com.saud.app.yaya.Database.IRepository;
import com.saud.app.yaya.Model.exercise;
import com.saud.app.yaya.Model.workout;

import java.util.List;

public interface IExercise<T> extends IRepository<exercise> {
    List<T> fetchExerciseOfworkout(String id);
}
