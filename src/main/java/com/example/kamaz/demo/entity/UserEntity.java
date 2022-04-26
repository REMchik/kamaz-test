package com.example.kamaz.demo.entity;

import com.example.kamaz.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user", schema = "public")
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column(name = "username")
    private String name;
    private int age;
    @NonNull
    private LocalDateTime  dateOfEmployment = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private PositionEntity position;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<TaskEntity> taskList;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    Set<GroupEntity> groups  = new HashSet<>();

    public User toModel() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setAge(this.age);
        user.setDateOfEmployment(this.dateOfEmployment);
        user.setPosition(this.position.getTitle());
        user.setGroups(this.getGroups());
        user.setTasks(this.taskList);

        return user;
    }
}


