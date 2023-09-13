package com.minibank.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minibank.demo.model.Account;
import com.minibank.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AccountRestControllerV1Test {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenAccountObject_whenCreateAccount_thenReturnSavedAccount() throws Exception{

        // given - precondition or setup
        Account account = Account.builder()
                .accountNumber("40817810000000000012")
                .userId(1L)
                .build();
        given(accountService.saveAccount(any(Account.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/bank/v1/accounts/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.iduser",
                        is(account.getUserId())))
                .andExpect(jsonPath("$.numaccount",
                        is(account.getAccountNumber())));
    }

    // JUnit test for Get All accounts REST API
    @Test
    public void givenListOfAccount_whenGetAllAccount_thenReturnAccountList() throws Exception{
        // given - precondition or setup
        List<Account> listOfAccounts = new ArrayList<>();
        listOfAccounts.add(Account.builder().accountNumber("40817810000000000012").userId(1L).build());
        listOfAccounts.add(Account.builder().accountNumber("40817810000000000013").userId(1L).build());
        given(accountService.findAccountAll()).willReturn(listOfAccounts);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/bank/v1/accounts/"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfAccounts.size())));

    }

    // positive scenario - valid account id
    // JUnit test for GET account by id REST API
    @Test
    public void givenAccountId_whenGetAccountById_thenReturnAccountObject() throws Exception{
        // given - precondition or setup
        long accountId = 1L;
        Account account = Account.builder()
                .accountNumber("40817810000000000012")
                .userId(1L)
                .build();
        given(accountService.findById(accountId)).willReturn(account);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/bank/v1/accounts/{id}", accountId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.numaccount", is(account.getAccountNumber())))
                .andExpect(jsonPath("$.iduser", is(account.getUserId())));
    }


    // JUnit test for update account REST API - positive scenario
    @Test
    public void givenUpdatedAccount_whenUpdateAccount_thenReturnUpdateAccountObject() throws Exception{
        // given - precondition or setup
        long accountId = 1L;
        Account savedAccount = Account.builder()
                .accountNumber("40817810000000000012")
                .userId(1L)
                .build();

        Account updatedAccount = Account.builder()
                .accountNumber("40817810000000000012")
                .userId(1L)
                .build();
        given(accountService.findById(accountId)).willReturn(savedAccount);
        given(accountService.saveAccount(any(Account.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/bank/v1/accounts/{id}", accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedAccount)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.numaccount", is(updatedAccount.getAccountNumber())))
                .andExpect(jsonPath("$.iduser", is(updatedAccount.getUserId())));
    }

    // JUnit test for delete account REST API
    @Test
    public void givenAccountId_whenDeleteAccount_thenReturn200() throws Exception{
        // given - precondition or setup
        long accountId = 1L;
        willDoNothing().given(accountService).deleteById(accountId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/bank/v1/accounts/{id}", accountId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}