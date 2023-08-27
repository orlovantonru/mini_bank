package com.minibank.demo.repository;

import com.minibank.demo.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserName("iwp");
        user.setEmail("iwp@gmail.com");
        user.setPassword("xxx");
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setFirstName("Ivanov");
        user.setSurName("Petrovich");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        user = null;
        userRepository.deleteAll();
    }

    // Test case SUCCESS
    @Test
    void testFindById()
    {
        User user = userRepository.findById(1L).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }
}