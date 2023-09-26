package com.minibank.demo.rest;

import com.minibank.demo.model.User;
import com.minibank.demo.response.ResponseHandler;
import com.minibank.demo.service.UserService;
import com.minibank.demo.model.Account;
import com.minibank.demo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class RestControllerV1
{
    UserService userService;
    @Value("${featire_flags.users_get}")
    private String featire_flags_users_get;

    @Value("${featire_flags.user_create}")
    private String featire_flags_user_create;

    public RestControllerV1(UserService userService) {

        this.userService = userService;

    }

    // Read Specific User Details from DB
    @GetMapping("/user/{userId}")
    @ApiOperation(value ="User id", notes="Provide user details",
            response = ResponseEntity.class)

    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") Long userId)
    {

        return ResponseHandler.responseBuilder("Requested User Details are given here",
                HttpStatus.OK, userService.findById(userId));
    }

    // Read All Users Details from DB
    @GetMapping("/user/")
    public List<User> getAllUserDetails()
    {
        if (featire_flags_users_get.equals("0")) {
            return null;
        }
        return userService.findAll();
    }


    @PostMapping("/user/")
    public String createUserDetails(@RequestBody User user)
    {
        if (featire_flags_user_create.equals("0")) {
            return null;
        }
        userService.saveUser(user);
        return "User Created Successfully";
    }


























































































































    @PutMapping("/user/")
    public String updateUserDetails(@RequestBody User user)
    {
        userService.saveUser(user);
        return "User Updated Successfully";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUserDetails(@PathVariable("userId") Long userId)
    {
        userService.deleteById(userId);
        return "User Deleted Successfully";
    }
}