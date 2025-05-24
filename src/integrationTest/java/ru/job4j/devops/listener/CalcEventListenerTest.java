package ru.job4j.devops.listener;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;
import ru.job4j.devops.fixtures.TestContainerKafkaConfig;
import ru.job4j.devops.fixtures.TestContainerPostgresqlConfig;
import ru.job4j.devops.fixtures.entity.CalcEventsUtil;
import ru.job4j.devops.models.CalcEvents;
import ru.job4j.devops.repository.CalcEventsRepository;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
public class CalcEventListenerTest {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(
            "postgres:16-alpine");

    private static final KafkaContainer KAFKA = new KafkaContainer(
            DockerImageName.parse("apache/kafka:3.7.2"));

    private static final CalcEvents CALC_EVENTS = CalcEventsUtil.validEntity();

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CalcEventsRepository calcEventsRepository;

    @BeforeAll
    static void beforeAll() {
        POSTGRES.start();
        KAFKA.start();
    }

    @AfterAll
    static void afterAll() {
        POSTGRES.start();
        KAFKA.stop();
    }

    @DynamicPropertySource
    public static void configureProperties(DynamicPropertyRegistry registry) {
        TestContainerKafkaConfig.configureProperties(registry, KAFKA);
        TestContainerPostgresqlConfig.configureProperties(registry, POSTGRES);
    }

    @Test
    void whenNewCalcEvent() {
        kafkaTemplate.send("calcEvents", CALC_EVENTS);

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    var optionalUser = calcEventsRepository.findByUserId(CALC_EVENTS.getUserId());
                    assertThat(optionalUser).isPresent();
                });
    }
}