package com.ute.bookstoreonlinebe.services.statistic;

import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.models.Statistic;
import com.ute.bookstoreonlinebe.models.StatisticByDay;
import com.ute.bookstoreonlinebe.repositories.OrderRepository;
import com.ute.bookstoreonlinebe.services.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService{
    private OrderService orderService;

    private OrderRepository orderRepository;

    public StatisticServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Statistic getTurnoverMonPresent() {
        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        System.out.println(date.get(weekFields.weekOfWeekBasedYear()));
        System.out.println(weekFields.weekOfWeekBasedYear());
        System.out.println(date.get(weekFields.dayOfWeek()));
        System.out.println(weekFields.dayOfWeek());
        List<Date> dates = new ArrayList<>();

        return null;
    }

    @Override
    public Statistic getTurnoverWeekPresent() {
        // get today and clear time of day
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        // get start of this week in milliseconds
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        System.out.println("Start of this week:       " + cal.getTime());
        System.out.println("... in milliseconds:      " + cal.getTimeInMillis());
        //System.out.println("First day of week: " + cal.get));

        List<Order> order = orderRepository.getOrderByOrderDate(cal);

        Statistic statistic = new Statistic();
        List<StatisticByDay> statisticByDays = new ArrayList<>();
        order.forEach(e -> {
            System.out.println(e.toString());
//            statisticByDays.add(new StatisticByDay(e.getOrderDate(), ));
        });
        return null;
    }

    @Override
    public Statistic getTurnoverByDay(Data data) {
        return null;
    }

    @Override
    public Statistic getTurnoverInDay() {
        return null;
    }
}
