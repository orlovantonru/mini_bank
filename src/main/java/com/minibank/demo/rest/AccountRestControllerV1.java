package com.minibank.demo.rest;

import com.minibank.demo.model.Account;
import com.minibank.demo.model.User;
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

    @Value("${featire_flags.account_create}")
    private String featire_flags_account_create;

    @Value("${featire_flags.account_get_by_user}")
    private String featire_flags_account_get_by_user;

    @Value("${featire_flags.account_save}")
    private String featire_flags_account_save;

    public AccountRestControllerV1(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("/account/{userId}")
    public List<Account> getAccountByUserDetails(@PathVariable("userId") Long userId) {
        return accountService.findByUserId(userId);
    }

    @PostMapping("/account/")
    public String createAccountDetails(@RequestBody Account account)
    {
        if (featire_flags_account_create.equals("0")) {
            return null;
        }
        accountService.saveAccount (account);
        return "Account Created Successfully";
    }

    @PutMapping("/account/")
    public String saveAccountDetails(@RequestBody Account account)
    {
        if (featire_flags_account_save.equals("0")) {
            return null;
        }
        accountService.saveAccount (account);
        return "Account Save Successfully";
    }
}