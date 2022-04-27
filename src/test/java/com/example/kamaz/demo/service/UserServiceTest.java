package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.PositionEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.User;
import com.example.kamaz.demo.repository.GroupRepository;
import com.example.kamaz.demo.repository.PositionRepository;
import com.example.kamaz.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    GroupRepository groupRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PositionRepository positionRepository;

    @Autowired
    UserService userService;

    @Test
    void testGetListSuccess() {
        int userId = 42;
        int userId2 = 12;
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setTitle("Конструктор");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Andrey");
        userEntity.setAge(33);
        userEntity.setPosition(positionEntity);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(userId2);
        userEntity2.setName("Vasya");
        userEntity2.setAge(35);
        userEntity2.setPosition(positionEntity);

        when(userRepository.findAll()).thenReturn(List.of(userEntity, userEntity2));

        List<User> result = userService.getList();
        int savedUserId = result.stream().findFirst().get().getId();

        assertThat(result.size()).isEqualTo(2);
        assertThat(savedUserId).isEqualTo(userId);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateSuccess() {
        int userId = 11;
        LocalDateTime timeNow = LocalDateTime.now();
        String position = "Конструктор";
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setTitle(position);
        User user = new User();
        user.setId(userId);
        user.setName("Andrey");
        user.setAge(33);
        user.setPosition(position);
        user.setGroups(Set.copyOf(Collections.emptyList()));
        user.setDateOfEmployment(timeNow);
        UserEntity savedEntity = user.toEntity();
        savedEntity.setPosition(positionEntity);

        when(userRepository.save(any())).thenReturn(savedEntity);
        when(positionRepository.findByTitle(user.getPosition())).thenReturn(positionEntity);
        when(groupRepository.findAllByIds(any())).thenReturn(Collections.emptyList());
        int result = userService.create(user);

        assertThat(result).isEqualTo(userId);
        verify(userRepository, times(1)).save(savedEntity);
    }

    @Test
    void testUpdateSuccess() {
        int userId = 11;
        LocalDateTime timeNow = LocalDateTime.now();
        String position = "Конструктор";
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setTitle(position);
        User user = new User();
        user.setId(userId);
        user.setName("Andrey");
        user.setAge(33);
        user.setPosition(position);
        user.setGroups(Set.copyOf(Collections.emptyList()));
        user.setDateOfEmployment(timeNow);
        UserEntity savedEntity = user.toEntity();
        savedEntity.setPosition(positionEntity);

        when(userRepository.save(any())).thenReturn(savedEntity);
        when(positionRepository.findByTitle(user.getPosition())).thenReturn(positionEntity);
        when(groupRepository.findAllByIds(any())).thenReturn(Collections.emptyList());

        userService.update(user);

        verify(userRepository, times(1)).save(savedEntity);
    }

    @Test
    void testDeleteSuccess() {
        int userId = 13;

        userService.delete(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}