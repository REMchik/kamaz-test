package com.example.kamaz.demo.entity;

import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "group", schema = "public")
public class GroupEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private LocalDateTime  dateOfEmployment = LocalDateTime.now();

    @JsonIgnore
    @ManyToMany(mappedBy = "groups")
    Set<UserEntity> users = new HashSet<>();

    public Group toModel() {
        Group group = new Group();
        group.setId(this.id);
        group.setTitle(this.title);
        group.setDateOfEmployment(this.dateOfEmployment);
        group.setUsers(this.users.stream().map(userEntity -> {
            User filedUser = new User();
            filedUser.setId(userEntity.getId());
            filedUser.setName(userEntity.getName());
            filedUser.setAge(userEntity.getAge());
            filedUser.setDateOfEmployment(userEntity.getDateOfEmployment());
            return filedUser;
        }).collect(Collectors.toSet()));

        return group;
    }
}

