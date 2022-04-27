package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.GroupDto;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.service.GroupService;
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
class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    GroupService groupService;

    @Test
    void testGetListSuccess() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/kamaz/group")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(groupService, times(1)).getList();
    }

    @Test
    void testCreateSuccess() throws Exception {
        GroupDto groupDto = new GroupDto();
        groupDto.setTitle("Group1");
        Group group = groupDto.toModel();

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/kamaz/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto))
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(groupService, times(1)).create(group);
    }

    @Test
    void testAddUserSuccess() throws Exception {
        int userId = 42;
        int groupId = 4;

        mvc.perform(
                MockMvcRequestBuilders
                        .patch("/kamaz/group/4/user/42")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andReturn();

        verify(groupService, times(1)).addUser(groupId, userId);
    }

    @Test
    void testDeleteSuccess() throws Exception {
        int groupId = 28;

        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/kamaz/group/28")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        verify(groupService, times(1)).delete(groupId);
    }
}