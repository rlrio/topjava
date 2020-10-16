package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if meal does not belong to user
    Meal save(Meal meal, int userId);

    // false if meal does not belong to user
    boolean delete(int id, int userId);

    // null if meal does not belong to user
    Meal get(int id, int userId);

    List<Meal> getAll(int userId);

    // selected dateTime desc
    List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
