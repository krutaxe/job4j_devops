package ru.job4j.devops.service;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.job4j.devops.fixtures.TestContainerPostgresqlConfig;
import ru.job4j.devops.fixtures.entity.UserUtil;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CalcServiceTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    ).withReuse(true);

    private static final User USER = UserUtil.validEntity();

    @Autowired
    private CalcService calcService;

    @Autowired
    private CalcEventsRepository calcEventsRepository;

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry) {
        TestContainerPostgresqlConfig.configureProperties(registry, POSTGRES);
    }

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();
    }

    @AfterAll
    static void afterAll() {
        POSTGRES.stop();
    }

    @Test
    public void whenAddCalcEvent() {
        USER.setId(1);
        calcService.add(USER, 3, 5);
        var foundCalcEvent = calcEventsRepository.findById(USER.getId());
        assertThat(foundCalcEvent).isPresent();
        assertThat(foundCalcEvent.get().getResult().equals(8));
    }
}
