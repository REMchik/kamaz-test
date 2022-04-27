package com.example.kamaz.demo.entity;

import com.example.kamaz.demo.model.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @Column(name = "date_create")
    private LocalDateTime createDate = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;

    public Task toModel() {
        Task task = new Task();
        task.setTitle(this.title);
        task.setUserId(this.user.getId());
        task.setCreateDate(this.createDate);

        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskEntity)) return false;
        TaskEntity that = (TaskEntity) o;
        return id == that.id && title.equals(that.title) && Objects.equals(createDate, that.createDate) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createDate, user);
    }
}
