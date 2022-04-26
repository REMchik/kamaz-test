package com.example.kamaz.demo.service;

import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Task;
import com.example.kamaz.demo.repository.TaskRepository;
import com.example.kamaz.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Task> getList(int userId) {
        return taskRepository.findAllByUserId(userId)
                .stream().map(TaskEntity::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public int create(Task task) {
        final UserEntity userEntity = userRepository.getById(task.getUserId());
        return taskRepository.save(task.toEntity(userEntity)).getId();
    }
}
