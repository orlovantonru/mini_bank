package com.minibank.demo.repository;

import com.minibank.demo.model.User;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    @Order(0)
    public void findById() {
        User user = userRepository.findById(1L).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    @Order(1)
    public void getListOfUserTest(){

        List<User> users = userRepository.findAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void updateUserTest(){

        User user = userRepository.findById(1L).get();

        user.setEmail("ram@gmail.com");

        User userUpdated =  userRepository.save(user);

        Assertions.assertThat(userUpdated.getEmail()).isEqualTo("ram@gmail.com");

    }

    @Test
    @Order(3)
    public void deleteUserTest(){

        User user = userRepository.findById(1L).get();

        userRepository.delete(user);

        //userRepository.deleteById(1L);

        User user1 = null;

        Optional<User> optionalUser = userRepository.findByUserName("iw");

        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        }

        Assertions.assertThat(user1).isNull();
    }
}