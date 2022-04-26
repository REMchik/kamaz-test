package com.example.kamaz.demo.dto;

import com.example.kamaz.demo.entity.UserEntity;
import com.example.kamaz.demo.model.Group;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto implements Serializable {
    private int id;
    private String title;
    private LocalDateTime  dateOfEmployment;

    Set<UserEntity> users = new HashSet<>();

    public Group toModel() {
        Group group = new Group();
        group.setId(this.id);
        group.setTitle(this.title);
        group.setDateOfEmployment(this.dateOfEmployment);

        return group;
    }
}