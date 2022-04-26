package com.example.kamaz.demo.entity;

import com.example.kamaz.demo.model.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @Column(name = "date_create")
    private LocalDateTime createDate = LocalDateTime.now();;
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
}
