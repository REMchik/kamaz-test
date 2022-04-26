package com.example.kamaz.demo.model;

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

    private Set<GroupEntity> groups = new HashSet<>();

    private List<TaskEntity> tasks = new ArrayList<>();
    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setName(this.name);
        userDto.setAge(this.age);
        userDto.setDateOfEmployment(this.dateOfEmployment);
        userDto.setPosition(this.position);
        userDto.setGroups(this.groups);
        userDto.setTasks(this.tasks);

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
