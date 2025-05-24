package ru.job4j.devops.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.devops.config.ContainersConfig;
import ru.job4j.devops.fixtures.entity.UserUtil;
import ru.job4j.devops.models.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest extends ContainersConfig {

    private static final User USER = UserUtil.validEntity();

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveUser() {
        userRepository.save(USER);
        var foundUser = userRepository.findById(USER.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("Job4j");
    }
}