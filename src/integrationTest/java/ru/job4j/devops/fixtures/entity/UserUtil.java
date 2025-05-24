package ru.job4j.devops.fixtures.entity;

import ru.job4j.devops.models.User;

public class UserUtil {
    public static User validEntity() {
        return User.builder()
                .username("Job4j")
                .build();
    }
}