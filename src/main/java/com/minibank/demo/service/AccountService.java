package com.minibank.demo.service;

import com.minibank.demo.exception.AccountNotFoundException;
import com.minibank.demo.exception.UserNotFoundException;
import com.minibank.demo.model.Account;
import com.minibank.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id){
        log.info("IN AccountService getById {}", id);
        if ((accountRepository.findById(id).isEmpty()))
            throw new AccountNotFoundException("Requested Account does not exist");
        return accountRepository.findById(id).get();
    }

    public List<Account> findAccountAll(){
        log.info("IN AccountService findAccountAll");
        return accountRepository.findAll();
    }

    public String saveAccount(Account account){
        log.info("IN AccountService saveUser {}", account);
        return "Success";
    }

    public String deleteById(Long id){
        log.info("IN AccountService deleteById {}", id);
        accountRepository.deleteById(id);
        return "Success";
    }

    public List<Account> findByUserId(Long id){
        log.info("IN AccountService findByUserId {}", id);
        return accountRepository.findByUserId(id);
    }
}