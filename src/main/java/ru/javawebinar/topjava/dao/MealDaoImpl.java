package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.exception.NullObjectException;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static final List<Meal> MEALS = new CopyOnWriteArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));
    AtomicInteger id = new AtomicInteger(MEALS.size());

    @Override
    public List<Meal> findAll() {
        return Collections.unmodifiableList(MEALS);
    }

    @Override
    public Meal findById(int id) throws NullObjectException {
        if (id != 0) throw new NullObjectException("Id cannot be 0");
        for (Meal meal : MEALS) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    @Override
    public void add(Meal meal) throws NullObjectException {
        if (!Objects.nonNull(meal)) throw new NullObjectException("Meal cannot be null");
        meal.setId(id.getAndIncrement());
        MEALS.add(meal);
    }

    @Override
    public void update(Meal meal) throws NullObjectException {
        if (!Objects.nonNull(meal)) throw new NullObjectException("Meal cannot be null");
        Meal oldMeal = findById(meal.getId());
        MEALS.remove(oldMeal);
        MEALS.add(meal);

    }

    @Override
    public void delete(int id) throws NullObjectException {
        if (id != 0) throw new NullObjectException("Id cannot be 0");
        MEALS.remove(findById(id));
    }
}
