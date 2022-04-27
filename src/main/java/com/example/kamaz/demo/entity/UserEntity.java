package com.example.kamaz.demo.entity;

import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.model.Task;
import com.example.kamaz.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private List<TaskEntity> taskList = new ArrayList<>();

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
        user.setGroups(this.groups.stream().map(groupEntity -> {
            Group filedGroup = new Group();
            filedGroup.setId(groupEntity.getId());
            filedGroup.setTitle(groupEntity.getTitle());
            filedGroup.setDateOfEmployment(groupEntity.getDateOfEmployment());
            return filedGroup;
        }).collect(Collectors.toSet()));
        user.setTasks(this.taskList.stream().map(taskEntity -> {
            Task task = new Task();
            task.setId(taskEntity.getId());
            task.setUserId(taskEntity.getUser().getId());
            task.setTitle(taskEntity.getTitle());
            task.setCreateDate(taskEntity.getCreateDate());
            return task;
                }).collect(Collectors.toList()));

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return getId() == that.getId() && getAge() == that.getAge() && getName().equals(that.getName()) && Objects.equals(getDateOfEmployment(), that.getDateOfEmployment()) && getPosition().equals(that.getPosition()) && Objects.equals(getTaskList(), that.getTaskList()) && Objects.equals(getGroups(), that.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getDateOfEmployment(), getPosition(), getTaskList(), getGroups());
    }
}


