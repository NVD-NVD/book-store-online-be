package com.ute.bookstoreonlinebe.services.statistic;

import com.ute.bookstoreonlinebe.models.Statistic;

import javax.xml.crypto.Data;

public interface StatisticService {

    Statistic getTurnoverMonPresent();

    Statistic getTurnoverWeekPresent();

    Statistic getTurnoverByDay(Data data);

    Statistic getTurnoverInDay();
}
