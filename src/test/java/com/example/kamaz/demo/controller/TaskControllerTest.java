package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.TaskDto;
import com.example.kamaz.demo.model.Task;
import com.example.kamaz.demo.service.TaskService;
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
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    TaskService taskService;

    @Test
    void testGetListSuccess() throws Exception {
        int userId = 42;

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/kamaz/task?userId=42")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(taskService, times(1)).getList(userId);
    }

    @Test
    void testCreateSuccess() throws Exception {
        int userId = 42;
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Task1");
        Task task = taskDto.toModel(userId);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/kamaz/task?userId=42")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(taskService, times(1)).create(task);
    }
}