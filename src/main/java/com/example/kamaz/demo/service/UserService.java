package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.PositionEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.model.User;
import com.example.kamaz.demo.repository.GroupRepository;
import com.example.kamaz.demo.repository.PositionRepository;
import com.example.kamaz.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PositionRepository positionRepository;

    private final GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public List<User> getList() {
        return userRepository.findAll()
                .stream().map(UserEntity::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public int create(User user) {
        return userRepository.save(getFilledUserEntity(user)).getId();
    }

    @Transactional
    public void update(User user) {
        userRepository.save(getFilledUserEntity(user));
    }

    @Transactional
    public void delete(int userId) {
        userRepository.deleteById(userId);
    }

    private UserEntity getFilledUserEntity(User user) {
        final PositionEntity positionEntity = positionRepository.findByTitle(user.getPosition());
        final UserEntity userEntity = user.toEntity();
        userEntity.setPosition(positionEntity);

        final List<Integer> groupsId = user.getGroups().stream().map(Group::getId).collect(Collectors.toList());
        final Set<GroupEntity> groupsEntity = Set.copyOf(groupRepository.findAllByIds(groupsId));
        userEntity.setGroups(groupsEntity);

        return userEntity;
    }
}
