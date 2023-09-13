package com.minibank.demo.service;

import com.minibank.demo.model.Account;
import com.minibank.demo.repository.AccountRepository;
import com.minibank.demo.service.AccountService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;
    AutoCloseable autoCloseable;
    Account account;

    Date date = new Date();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountRepository);
        account = new Account(1L,"40817810000000000013",date, 1L);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testUpdateAccount() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.save(account)).thenReturn(account);
        assertThat(accountService.saveAccount(account)).isEqualTo("Success");
    }

    @Test
    void testDeleteAccount() {
        mock(Account.class);
        mock(AccountRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(accountRepository)
                .deleteById(any());
        assertThat(accountService.deleteById(1L)).isEqualTo("Success");
    }

    @Test
    void testGetAccount() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));

        assertThat(accountService.findById(1L).getAccountNumber())
                .isEqualTo(account.getAccountNumber());
    }

    @Test
    void testGetAllAccounts() {
        mock(Account.class);
        mock(AccountRepository.class);

        when(accountRepository.findAll()).thenReturn(new ArrayList<Account>(
                Collections.singleton(account)
        ));

        assertThat(accountService.findAccountAll().get(0).getAccountNumber()).
                isEqualTo(account.getAccountNumber());

    }
}