package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao mealDao;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MealServlet() {
        mealDao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        log.debug("redirect to meals");

        String forward = "";
        switch (action == null ? "all" : action) {
            case "delete":
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                mealDao.delete(mealId);
                request.setAttribute("meals", getAllMeals());
                response.sendRedirect("meals");
                break;
            case "create":
                forward = "/mealUpdateOrCreate.jsp";
                break;
            case "update":
                int mealIdToEdit = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealDao.findById(mealIdToEdit);
                request.setAttribute("meal", meal);
                forward = "/mealUpdateOrCreate.jsp";
                break;
            default:
                request.setAttribute("meals", getAllMeals());
                forward = "/meals.jsp";
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.from(dateTimeFormatter.parse(request.getParameter("dateTime")));

        Meal meal = new Meal(dateTime, description, calories);

        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            mealDao.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            mealDao.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        request.setAttribute("meals", getAllMeals());
        view.forward(request, resp);
    }

    private List<MealTo> getAllMeals() {
        return MealsUtil.filteredByCycles(mealDao.findAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}
