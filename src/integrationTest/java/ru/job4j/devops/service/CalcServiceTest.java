package ru.job4j.devops.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.fixtures.entity.UserUtil;
import ru.job4j.devops.models.User;
import ru.job4j.devops.repository.CalcEventsRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CalcServiceTest extends ContainersConfig {

    private static final User USER = UserUtil.validEntity();

    @Autowired
    private CalcService calcService;

    @Autowired
    private CalcEventsRepository calcEventsRepository;

    @Test
    public void whenAddCalcEvent() {
        USER.setId(1);
        calcService.add(USER, 3, 5);
        var foundCalcEvent = calcEventsRepository.findById(USER.getId());
        assertThat(foundCalcEvent).isPresent();
        assertThat(foundCalcEvent.get().getResult().equals(8));
    }
}
