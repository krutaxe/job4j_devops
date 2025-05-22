package ru.job4j.devops.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.devops.models.CalcEvents;

public interface CalcEventsRepository extends CrudRepository<CalcEvents, Integer> {
}
