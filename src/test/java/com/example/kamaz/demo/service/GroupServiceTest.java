package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.PositionEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.repository.GroupRepository;
import com.example.kamaz.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GroupServiceTest {

    @MockBean
    GroupRepository groupRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    GroupService groupService;

    @Test
    void testGetListSuccess() {
        int userId = 42;
        GroupEntity groupEntity1 = new GroupEntity();
        GroupEntity groupEntity2 = new GroupEntity();
        groupEntity1.setTitle("Group1");
        groupEntity2.setTitle("Group2");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Andrey");
        userEntity.setAge(33);
        groupEntity1.setUsers(Set.of(userEntity));
        groupEntity2.setUsers(Set.of(userEntity));

        when(groupRepository.findAll()).thenReturn(List.of(groupEntity1, groupEntity2));

        List<Group> result = groupService.getList();
        int savedUserId = result.stream().findFirst().get().getUsers().stream().findFirst().get().getId();

        assertThat(result.size()).isEqualTo(2);
        assertThat(savedUserId).isEqualTo(userId);
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    void testCreateSuccess() {
        int groupId = 88;
        Group group = new Group();
        group.setTitle("Group");

        GroupEntity savedEntity = group.toEntity();
        savedEntity.setId(groupId);
        when(groupRepository.save(any())).thenReturn(savedEntity);

        int result = groupService.create(group);

        assertThat(result).isEqualTo(groupId);
        verify(groupRepository, times(1)).save(any());
    }

    @Test
    void testAddUser() {
        int groupId = 14;
        int userId = 56;
        LocalDateTime timeNow = LocalDateTime.now();
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setTitle("Конструктор");
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setTitle("Group1");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("Vasya");
        userEntity.setAge(26);
        userEntity.setDateOfEmployment(timeNow);
        userEntity.setPosition(positionEntity);
        userEntity.setGroups(Set.of(groupEntity));
        GroupEntity newGroup = new GroupEntity();
        newGroup.setTitle("newGroup");

        UserEntity filledUserEntity = new UserEntity();
        filledUserEntity.setId(userId);
        filledUserEntity.setName("Vasya");
        filledUserEntity.setAge(26);
        filledUserEntity.setDateOfEmployment(timeNow);
        filledUserEntity.setPosition(positionEntity);
        filledUserEntity.setGroups(Set.of(groupEntity, newGroup));

        when(userRepository.getById(userId)).thenReturn(userEntity);
        when(groupRepository.getById(groupId)).thenReturn(newGroup);

        groupService.addUser(groupId, userId);

        verify(userRepository, times(1)).save(filledUserEntity);
        verify(groupRepository, times(0)).save(any());
    }


    @Test
    void testDeleteSuccess() {
        int groupId = 77;

        groupService.delete(groupId);

        verify(groupRepository, times(1)).deleteById(groupId);
    }
}