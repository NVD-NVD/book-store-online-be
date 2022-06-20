package com.ute.bookstoreonlinebe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Statistic {
    private List<StatisticInDay> days = new ArrayList<>();
}
