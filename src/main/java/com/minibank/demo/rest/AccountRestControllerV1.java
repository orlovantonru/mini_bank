package com.minibank.demo.rest;

import com.minibank.demo.model.Account;
import com.minibank.demo.response.ResponseHandler;
import com.minibank.demo.service.AccountService;
import com.minibank.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")

public class AccountRestControllerV1 {

    AccountService accountService;

    public AccountRestControllerV1(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("/account/{userId}")
    public List<Account> getAccountByUserDetails(@PathVariable("userId") Long userId) {
        return accountService.findByUserId(userId);
    }
}