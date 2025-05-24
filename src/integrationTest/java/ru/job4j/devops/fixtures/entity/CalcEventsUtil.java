package ru.job4j.devops.fixtures.entity;

import ru.job4j.devops.models.CalcEvents;

import java.time.LocalDate;

public class CalcEventsUtil {
    public static CalcEvents validEntity() {
        return CalcEvents.builder()
                .userId(1)
                .first(3)
                .second(4)
                .result(7)
                .createDate(LocalDate.now())
                .type("+")
                .build();
    }
}