package com.ute.bookstoreonlinebe.services.statistic;

import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.exceptions.NotFoundException;
import com.ute.bookstoreonlinebe.models.Statistic;
import com.ute.bookstoreonlinebe.models.StatisticByDay;
import com.ute.bookstoreonlinebe.repositories.OrderRepository;
import com.ute.bookstoreonlinebe.services.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService{
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("UTC");
    @Autowired
    private MongoOperations mongoOperations;

    private OrderService orderService;

    private OrderRepository orderRepository;

    public StatisticServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Statistic getTurnoverMonPresent() {
        System.out.println("Start of month: " + toString(startOfMonth()));
        System.out.println("End of month: " + toString(endOfMonth()));
        Query query = new Query();
        query.addCriteria(Criteria.where("orderDate")
                .gt(toString(startOfMonth()))
                .lt(toString(endOfMonth()))
        );
        List<Order> orders = mongoOperations.find(query, Order.class);
        orders.forEach(e -> System.out.println(e.toString()));
        return null;
    }

    @Override
    public Statistic getTurnoverWeekPresent() {
        System.out.println("Start of week: " + toString(startOfWeek()));
        System.out.println("End of week: " + toString(endOfWeek()));
        Query query = new Query();
        query.addCriteria(Criteria.where("orderDate")
                .gt(toString(startOfWeek()))
                .lt(toString(endOfWeek()))
        );
        List<Order> orders = mongoOperations.find(query, Order.class);
//        List<Order> orders = orderRepository.getOrderByOrderDateToDate(
//                "2022-06-01","2022-06-30"
//        ).orElseThrow(() -> new NotFoundException(String.format("Không tìm thấy các order theo tuần!")));
        orders.forEach(e -> System.out.println(e.toString()));
        return new Statistic();
    }

    @Override
    public Statistic getTurnoverByDay(Data data) {
        return null;
    }

    @Override
    public Statistic getTurnoverInDay() {
        System.out.println("Start of day: " + toString(startOfDay()));
        System.out.println("End of day: " + toString(endOfDay()));
        List<Order> orders = orderRepository.getOrderByOrderDate("2022-06-01");

        return null;
    }
    public static LocalDateTime startOfDay() {
        return LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MIN);
    }

    public static LocalDateTime endOfDay() {
        return LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MAX);
    }

    public static LocalDateTime startOfWeek() {
        return LocalDateTime.now(DEFAULT_ZONE_ID)
                .with(LocalTime.MIN)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    //note that week ends with Sunday
    public static LocalDateTime endOfWeek() {
        return LocalDateTime.now(DEFAULT_ZONE_ID)
                .with(LocalTime.MAX)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }
    public static LocalDateTime startOfMonth() {
        return LocalDateTime.now(DEFAULT_ZONE_ID)
                .with(LocalTime.MIN)
                .with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDateTime endOfMonth() {
        return LocalDateTime.now(DEFAULT_ZONE_ID)
                .with(LocalTime.MAX)
                .with(TemporalAdjusters.lastDayOfMonth());
    }

    public static String toString(final LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
