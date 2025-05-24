package ru.job4j.devops.fixtures;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerPostgresqlConfig {
    public static void configureProperties(DynamicPropertyRegistry registry,
                                           PostgreSQLContainer<?> container) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
