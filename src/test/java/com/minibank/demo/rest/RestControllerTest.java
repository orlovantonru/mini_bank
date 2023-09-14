package com.minibank.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.minibank.demo.model.User;
import com.minibank.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RestControllerV1.class)
class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    User userOne;
    User userTwo;
    List<User> userList= new ArrayList<>();

    @BeforeEach
    void setUp() {
        userOne = new User(1L,"Ivan","Ivanov", "Ivanovich","iii","iii@mail.com","xxx");
        userTwo = new User(1L,"Petr","Petrov", "Petrovich","ppp","ppp@mail.com","xxx");
        userList.add(userOne);
        userList.add(userTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserDetails() throws Exception {
        when(userService.findById(1L)).thenReturn(userOne);
        this.mockMvc.perform(get("/bank/user/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getUsersDetails() throws  Exception {
        when(userService.findAll()).thenReturn(userList);
        this.mockMvc.perform(get("/bank/user"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createUserDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userOne);

        when(userService.saveUser(userOne)).thenReturn("Success");
        this.mockMvc.perform(post("/bank/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateUserDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userOne);

        when(userService.saveUser(userOne))
                .thenReturn("User Updated Successfully");
        this.mockMvc.perform(put("/bank/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteUserDetails() throws Exception {
        when(userService.deleteById(1L))
                .thenReturn("User Deleted Successfully");
        this.mockMvc.perform(delete("/bank/user/" + "1"))
                .andDo(print()).andExpect(status().isOk());

    }
}