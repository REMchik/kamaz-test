package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Task;
import com.example.kamaz.demo.repository.TaskRepository;
import com.example.kamaz.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskServiceTest {

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Test
    void testGetListSuccess() {
        int userId = 42;
        TaskEntity taskEntity1 = new TaskEntity();
        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity1.setTitle("Work1");
        taskEntity2.setTitle("Work2");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Andrey");
        userEntity.setAge(33);
        taskEntity1.setUser(userEntity);
        taskEntity2.setUser(userEntity);

        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(taskEntity1);
        taskEntities.add(taskEntity2);

        when(taskRepository.findAllByUserId(userId)).thenReturn(taskEntities);

        List<Task> result = taskService.getList(userId);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.stream().findFirst().get().getUserId()).isEqualTo(userId);
        verify(taskRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void testCreateSuccess() {
        int taskId = 88;
        int userId = 42;
        Task task = new Task();
        task.setTitle("Work");
        task.setUserId(userId);
        TaskEntity savedEntity = new TaskEntity();
        savedEntity.setTitle("Work");
        savedEntity.setId(taskId);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Vasya");
        userEntity.setAge(33);
        savedEntity.setUser(userEntity);

        when(userRepository.getById(userId)).thenReturn(userEntity);
        when(taskRepository.save(any())).thenReturn(savedEntity);

        int result = taskService.create(task);

        assertThat(result).isEqualTo(taskId);
        verify(taskRepository, times(1)).save(any());
    }
}