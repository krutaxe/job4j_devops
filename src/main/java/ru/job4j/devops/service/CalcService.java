package ru.job4j.devops.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.devops.models.CalcEvents;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventsRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalcService {

    private final CalcEventsRepository calcEventsRepository;

    public void add(User user, Integer first, Integer second) {
        Integer result = first + second;
        CalcEvents calcEvents = new CalcEvents(user.getId(), first, second,
                result, LocalDate.now(), "+");
        calcEventsRepository.save(calcEvents);
        log.info("method ADD was executed successfully");
    }
}