package ru.job4j.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.job4j.devops.models.User;
import ru.job4j.devops.service.CalcService;

@SpringBootApplication
public class CalcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalcApplication.class, args);
	}

	@Bean
	public String addTest(CalcService calcService) {
		User user = new User(1, "Job4j");

		calcService.add(user, 3, 5);
		return "Hello World";
	}
}