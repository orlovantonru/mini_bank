package com.minibank.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minibank.demo.model.Account;
import com.minibank.demo.service.AccountService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class test {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

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
