package com.minibank.demo.service;

import com.minibank.demo.model.User;
import com.minibank.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        log.info("IN UserService getById {}", id);
        return userRepository.getOne(id);
    }

    public List<User> findAll(){
        log.info("IN UserService findAll {}");
        return userRepository.findAll();
    }

    public User saveUser(User user){
        log.info("IN UserService saveUser {}", user);
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        log.info("IN UserService deleteById {}", id);
        userRepository.deleteById(id);
    }
}
