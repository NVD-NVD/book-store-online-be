package com.ute.bookstoreonlinebe.models;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class StatisticByDay {
    private Date day;
    private long sumbills;
    private float summonny;
}
