package com.ute.bookstoreonlinebe.services.statistic;

import com.ute.bookstoreonlinebe.models.Statistic;
import com.ute.bookstoreonlinebe.services.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService{
    private OrderService orderService;

    public StatisticServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Statistic getTurnoverMonPresent() {

        return null;
    }

    @Override
    public Statistic getTurnoverInDay() {
        return null;
    }
}
