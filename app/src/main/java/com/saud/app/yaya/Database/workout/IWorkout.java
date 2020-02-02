package com.saud.app.yaya.Database.workout;

import com.saud.app.yaya.Database.IRepository;
import com.saud.app.yaya.Model.workout;

public interface IWorkout<T> extends IRepository<workout> {
    T fetchExerciseOfworkout(String id);
}
