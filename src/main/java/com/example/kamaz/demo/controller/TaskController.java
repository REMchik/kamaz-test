package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.TaskDto;
import com.example.kamaz.demo.model.Task;
import com.example.kamaz.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kamaz/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getList(@RequestParam int userId) {
        return taskService.getList(userId)
                .stream().map(Task::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public int create(@RequestParam int userId, @RequestBody TaskDto taskDto) {
        return taskService.create(taskDto.toModel(userId));
    }
}
