package com.minibank.demo.rest;

import com.minibank.demo.model.Account;
import com.minibank.demo.model.User;
import com.minibank.demo.service.AccountService;
import com.minibank.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestControllerV1.class)
public class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
   // private UserService userService;
    private AccountService accountService;
    User userOne;
    User userTwo;
    List<User> userList= new ArrayList<>();

    Account accountOne;
    Account accountTwo;
    List<Account> accountList = new ArrayList<>();
    Date date = new Date();
    @BeforeEach
    void setUp() {
        userOne = new User(1L,"Ivan","Ivanov", "Ivanovich","iii","iii@mail.com","xxx");
        userTwo = new User(1L,"Petr","Petrov", "Petrovich","ppp","ppp@mail.com","xxx");
        userList.add(userOne);
        userList.add(userTwo);

        accountOne = new Account(1L,"40817810000000000012",date,1L, 10F, 100000F);
        accountTwo = new Account(2L,"40817810000000000013",date,1L, 9F, 50000F);
        accountList.add(accountOne);
        accountList.add(accountTwo);
    }

    @AfterEach
    void tearDown() {
    }

 /*   @Test
    void getAccountByUserDetailsTest() throws Exception {
        when(accountService.findByUserId(1L)).thenReturn(accountList);
        this.mockMvc.perform(get("/bank/account/" + "1")).andDo(print()).andExpect(status().isOk());
    }*/
}
