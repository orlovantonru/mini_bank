package com.minibank.demo.rest;

import com.minibank.demo.model.User;
import com.minibank.demo.response.ResponseHandler;
import com.minibank.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/user")
public class RestControllerV1
{
    UserService userService;

    public RestControllerV1(UserService userService) {
        this.userService = userService;
    }

    // Read Specific User Details from DB
    @GetMapping("/{userId}")
    @ApiOperation(value ="User id", notes="Provide user details",
            response = ResponseEntity.class)

    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") Long userId)
    {
        return ResponseHandler.responseBuilder("Requested User Details are given here",
                HttpStatus.OK, userService.findById(userId));
    }

    // Read All Users Details from DB
    @GetMapping("/")
    public List<User> getAllUserDetails()
    {
        return userService.findAll();
    }

    @PostMapping("/")
    public String createUserDetails(@RequestBody User user)
    {
        userService.saveUser(user);
        return "User Created Successfully";
    }

    @PutMapping("/")
    public String updateUserDetails(@RequestBody User user)
    {
        userService.saveUser(user);
        return "User Updated Successfully";
    }

    @DeleteMapping("/{userId}")
    public String deleteUserDetails(@PathVariable("userId") Long userId)
    {
        userService.deleteById(userId);
        return "User Deleted Successfully";
    }
}