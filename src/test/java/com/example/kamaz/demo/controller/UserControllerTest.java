package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.UserDto;
import com.example.kamaz.demo.model.User;
import com.example.kamaz.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    void testGetListSuccess() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/kamaz/user")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(userService, times(1)).getList();
    }

    @Test
    void testCreateSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Andrey");
        userDto.setPosition("Менеджер");
        User user = userDto.toModel();

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/kamaz/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(userService, times(1)).create(user);
    }

    @Test
    void testUpdateSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Andrew");
        userDto.setPosition("Менеджер");
        User user = userDto.toModel();

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/kamaz/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(userService, times(1)).create(user);
    }

    @Test
    void testDeleteSuccess() throws Exception {
        int userId = 2;

        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/kamaz/user/2")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService, times(1)).delete(userId);
    }
}