package com.example.kamaz.demo.model;

import com.example.kamaz.demo.dto.GroupDto;
import com.example.kamaz.demo.dto.TaskDto;
import com.example.kamaz.demo.dto.UserDto;
import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class User {

    private int id;
    @NonNull
    private String name;
    private int age;
    private LocalDateTime  dateOfEmployment;
    @NonNull
    private String position;

    private Set<Group> groups = new HashSet<>();

    private List<Task> tasks = new ArrayList<>();
    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setName(this.name);
        userDto.setAge(this.age);
        userDto.setDateOfEmployment(this.dateOfEmployment);
        userDto.setPosition(this.position);
        userDto.setGroups(this.groups.stream().map(group -> {
            GroupDto filedGroupDto = new GroupDto();
            filedGroupDto.setId(group.getId());
            filedGroupDto.setTitle(group.getTitle());
            filedGroupDto.setDateOfEmployment(group.getDateOfEmployment());
            return filedGroupDto;
        }).collect(Collectors.toSet()));
        userDto.setTasks(this.tasks.stream().map(task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setTitle(task.getTitle());
            taskDto.setCreateDate(task.getCreateDate());
            return taskDto;
        }).collect(Collectors.toList()));

        return userDto;
    }

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.id);
        userEntity.setName(this.name);
        userEntity.setAge(this.age);
        if(this.getDateOfEmployment() != null ) {
            userEntity.setDateOfEmployment(this.dateOfEmployment);
        }

        return userEntity;
    }
}
