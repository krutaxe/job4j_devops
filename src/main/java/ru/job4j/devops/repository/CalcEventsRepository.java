package ru.job4j.devops.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.CalcEvents;

import java.util.Optional;

public interface CalcEventsRepository extends CrudRepository<CalcEvents, Integer> {

    Optional<CalcEvents> findByUserId(Integer userId);
}
