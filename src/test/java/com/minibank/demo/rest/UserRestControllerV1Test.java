package com.minibank.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minibank.demo.model.User;
import com.minibank.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class UserRestControllerV1Test {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{

        // given - precondition or setup
        User user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .surName("Ivanovich")
                .email("ivan@gmail.com")
                .userName("ivv")
                .password("565fdrtr")
                .build();
        given(userService.saveUser(any(User.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/bank/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(user.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(user.getEmail())))
                .andExpect(jsonPath("$.surName",
                        is(user.getSurName())))
                .andExpect(jsonPath("$.userName",
                        is(user.getUserName())))
                .andExpect(jsonPath("$.password",
                        is(user.getPassword())));
    }

    // JUnit test for Get All users REST API
    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnEUsersList() throws Exception{
        // given - precondition or setup
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(User.builder().firstName("Ivan").lastName("Ivanov").email("ivan@gmail.com").build());
        listOfUsers.add(User.builder().firstName("Petr").lastName("Petrov").email("petr@gmail.com").build());
        given(userService.findAll()).willReturn(listOfUsers);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/bank/v1/users/"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsers.size())));

    }

    // positive scenario - valid user id
    // JUnit test for GET user by id REST API
    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception{
        // given - precondition or setup
        long userId = 1L;
        User user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .surName("Ivanovich")
                .email("ivan@gmail.com")
                .userName("ivv")
                .password("565fdrtr")
                .build();
        given(userService.findById(userId)).willReturn(user);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/bank/v1/users/{id}", userId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.surName", is(user.getSurName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.userName", is(user.getUserName())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));

    }
}
