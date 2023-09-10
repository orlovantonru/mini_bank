package com.minibank.demo.service;

import com.minibank.demo.model.Account;
import com.minibank.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return accountRepository.getOne(id);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account saveAccount(Account account){
        log.info("IN AccountService saveUser {}", account);
        return accountRepository.save(account);
    }
    public Account updateAccount(Account updatedAccount) {
        return accountRepository.save(updatedAccount);
    }
    public void deleteById(Long id){
        log.info("IN AccountService deleteById {}", id);
        accountRepository.deleteById(id);
    }
}