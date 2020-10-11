package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.exception.NullObjectException;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> findAll();

    Meal findById(int id) throws NullObjectException;

    void add(Meal meal) throws NullObjectException;

    void update(Meal meal) throws NullObjectException;

    void delete(int id) throws NullObjectException;
}
