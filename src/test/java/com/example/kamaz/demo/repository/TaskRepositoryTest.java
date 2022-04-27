package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.KamazDemoApplication;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KamazDemoApplication.class)
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void testSaveAndGetSuccess() {
        int userId = 42;
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Title");
        taskEntity.setCreateDate(LocalDateTime.now());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        taskEntity.setUser(userEntity);

        taskRepository.save(taskEntity);

        assertNotNull(taskRepository.findAllByUserId(userId));
    }
}