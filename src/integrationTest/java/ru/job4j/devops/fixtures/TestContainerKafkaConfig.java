package ru.job4j.devops.fixtures;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.kafka.KafkaContainer;

public class TestContainerKafkaConfig {
    public static void configureProperties(DynamicPropertyRegistry registry,
                                           KafkaContainer container) {
        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
        registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers);
    }
}
