package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.List;

public interface MealRepository {

    Meal get(int id);

    Collection<Meal> getAll();

    boolean delete(int id);

    Meal save(Meal meal);

}
