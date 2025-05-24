package ru.job4j.devops.listener;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.devops.models.CalcEvents;
import ru.job4j.devops.models.User;
import ru.job4j.devops.service.CalcService;

@Component
@AllArgsConstructor
@Slf4j
public class CalcEventListener {

    private final CalcService calcService;

    @KafkaListener(topics = "calcEvents", groupId = "job4j")
    public void calcEvents(CalcEvents calcEvents) {
        log.info("Received numbs: {} and {}", calcEvents.getFirst(), calcEvents.getSecond());
        calcService.add(new User(calcEvents.getUserId(), "CalcEvent"),
                calcEvents.getFirst(), calcEvents.getSecond());
    }
}