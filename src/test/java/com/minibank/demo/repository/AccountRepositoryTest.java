package com.minibank.demo.repository;

import com.minibank.demo.model.Account;
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
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Order(0)
    public void findById() {
        Account account = accountRepository.findById(1L).get();
        Assertions.assertThat(account.getId()).isEqualTo(1L);
    }

    @Test
    @Order(1)
    public void getListOfAccountTest(){

        List<Account> account = accountRepository.findAll();

        Assertions.assertThat(account.size()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void updateAccountTest(){

        Account account = accountRepository.findById(1L).get();

        account.setAccountNumber("40817810000000000012");

        Account accountUpdated =  accountRepository.save(account);

        Assertions.assertThat(accountUpdated.getAccountNumber()).isEqualTo("40817810000000000012");

    }

    @Test
    @Order(3)
    public void deleteAccountTest(){

        Account account = accountRepository.findById(1L).get();

        accountRepository.delete(account);

        Account account1 = null;

        Optional<Account> optionalAccount = accountRepository.findByUserId(1L);

        if(optionalAccount.isPresent()){
            account1 = optionalAccount.get();
        }

        Assertions.assertThat(account1).isNull();
    }
}