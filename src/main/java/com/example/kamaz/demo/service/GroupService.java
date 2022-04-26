package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.repository.GroupRepository;
import com.example.kamaz.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Group> getList() {
        return groupRepository.findAll()
                .stream().map(GroupEntity::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public int create(Group group) {
        return groupRepository.save(group.toEntity()).getId();
    }

    @Transactional
    public void addUser(int groupId, int userId) {
        final UserEntity userEntity = userRepository.getById(userId);
        final GroupEntity groupEntity = groupRepository.getById(groupId);
        final Set<GroupEntity> filledGroups = userEntity.getGroups();
        final UserEntity filledEntity = new UserEntity();

        filledGroups.add(groupEntity);

        filledEntity.setId(userEntity.getId());
        filledEntity.setName(userEntity.getName());
        filledEntity.setAge(userEntity.getAge());
        filledEntity.setPosition(userEntity.getPosition());
        filledEntity.setDateOfEmployment(userEntity.getDateOfEmployment());
        filledEntity.setGroups(filledGroups);

        userRepository.save(filledEntity);
    }

    @Transactional
    public void delete(int groupId) {
        groupRepository.deleteById(groupId);
    }
}
