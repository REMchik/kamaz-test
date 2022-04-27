package com.example.kamaz.demo.dto;

import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable{

    private int id;
    @NonNull
    private String name;
    private int age;
    private LocalDateTime  dateOfEmployment;
    @NonNull
    private String position;

    private Set<GroupDto> groups  = new HashSet<>();

    private List<TaskDto> tasks = new ArrayList<>();

    public User toModel() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setAge(this.age);
        user.setDateOfEmployment(this.dateOfEmployment);
        user.setPosition(this.position);
        user.setGroups(this.groups.stream().map( groupDto -> {
            Group group = new Group();
            group.setId(groupDto.getId());
            group.setTitle(groupDto.getTitle());
            group.setDateOfEmployment(groupDto.getDateOfEmployment());
            return group;
                }).collect(Collectors.toSet()));

        return user;
    }
}
