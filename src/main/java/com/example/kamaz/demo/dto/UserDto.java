package com.example.kamaz.demo.dto;

import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable{
    private int id;
    private String name;
    private int age;
    private LocalDateTime  dateOfEmployment;
    private String position;

    private Set<GroupEntity> groups  = new HashSet<>();

    private List<TaskEntity> tasks = new ArrayList<>();

    public User toModel() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setAge(this.age);
        user.setDateOfEmployment(this.dateOfEmployment);
        user.setPosition(this.position);
        user.setGroups(this.groups);

        return user;
    }
}
