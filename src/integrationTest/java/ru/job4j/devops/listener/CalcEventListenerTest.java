package ru.job4j.devops.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.fixtures.entity.CalcEventsUtil;
import ru.job4j.devops.models.CalcEvents;
import ru.job4j.devops.repository.CalcEventsRepository;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CalcEventListenerTest extends ContainersConfig {

    private static final CalcEvents CALC_EVENTS = CalcEventsUtil.validEntity();

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CalcEventsRepository calcEventsRepository;

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