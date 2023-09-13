package com.minibank.demo.service;

import com.minibank.demo.exception.UserNotFoundException;
import com.minibank.demo.model.User;
import com.minibank.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

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
        if ((userRepository.findById(id).isEmpty()))
            throw new UserNotFoundException("Requested User does not exist");
        return userRepository.findById(id).get();
    }

    public List<User> findAll(){
        log.info("IN UserService findAll");
        return userRepository.findAll();
    }

    public String saveUser(User user){
        log.info("IN UserService saveUser {}", user);
        return "Success";
    }

    public String deleteById(Long id){
        log.info("IN UserService deleteById {}", id);
        userRepository.deleteById(id);
        return "Success";
    }
}