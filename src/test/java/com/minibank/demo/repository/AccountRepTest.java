package com.minibank.demo.repository;

import com.minibank.demo.model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class AccountRepTest {

    @Autowired
    private AccountRepository accountRepository;
    Account account;
    Date date = new Date();

    @BeforeEach
    void setUp() {
        account = new Account(1L,"40817810000000000012",
                date, 1L);
        accountRepository.save(account);
    }

    @AfterEach
    void tearDown() {
        account = null;
        accountRepository.deleteAll();
    }

    @Test
        public void findById() {
        Account account = accountRepository.findById(1L).get();
        Assertions.assertThat(account.getId()).isEqualTo(1L);
    }

    @Test
    public void getListOfAccountTest(){

        List<Account> account = accountRepository.findAll();
        Assertions.assertThat(account.size()).isGreaterThan(0);

    }


    @Test
    public void updateAccountTest(){
        if (accountRepository.findById(1L).isPresent()) {
            Account account = accountRepository.findById(1L).get();

            account.setAccountNumber("40817810000000000013");

            Account accountUpdated = accountRepository.save(account);

            Assertions.assertThat(accountUpdated.getAccountNumber()).isEqualTo("40817810000000000013");
        }
    }

    @Test
    public void deleteAccountTest() {

        if (accountRepository.findById(1L).isPresent()) {
            Account account = accountRepository.findById(1L).get();

            accountRepository.delete(account);

            Account account1 = null;

            Optional<Account> optionalAccount = accountRepository.findById(1L);

            if (optionalAccount.isPresent()) {
                account1 = optionalAccount.get();
            }

            Assertions.assertThat(account1).isNull();
        }
    }

    @Test
    public void findByUserIdTest() {
        List<Account> account = accountRepository.findByUserId(1L);
        Assertions.assertThat(account.size()).isGreaterThan(0);
    }
}
