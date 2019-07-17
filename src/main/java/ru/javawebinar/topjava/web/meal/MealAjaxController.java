package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController {
    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createWithLocation(@RequestParam("id") Integer id,
                                   @RequestParam("dateTime") String dateTime,
                                   @RequestParam("description") String description,
                                   @RequestParam("calories") int calories) {

        super.create(new Meal(id, LocalDateTime.parse(dateTime), description, calories));
    }

    @PostMapping(value = "/filter")
    public List<MealTo> getBetween(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String endTime) {
        final LocalDate ldStartDate = DateTimeUtil.parseLocalDate(startDate);
        final LocalTime ltStartTime = DateTimeUtil.parseLocalTime(startTime);
        final LocalDate ldEndDate = DateTimeUtil.parseLocalDate(endDate);
        final LocalTime ltEndTime = DateTimeUtil.parseLocalTime(endTime);

        return super.getBetween(ldStartDate, ltStartTime, ldEndDate, ltEndTime);
    }
}
