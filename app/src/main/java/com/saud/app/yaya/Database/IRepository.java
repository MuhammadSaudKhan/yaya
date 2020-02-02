package com.saud.app.yaya.Database;

import com.saud.app.yaya.Model.workout;

import java.util.List;

public interface IRepository<T> {
    long save(T t);
    boolean update(T t);
    boolean remove(String id);
    List<T> fetchAll();
    T fetchById(String id);

}
