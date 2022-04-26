package com.example.kamaz.demo.model;

import com.example.kamaz.demo.dto.TaskDto;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task {

    private int id;
    @NonNull
    private String title;
    @NonNull
    private LocalDateTime createDate;
    private int userId;

    public TaskDto toDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(this.title);
        taskDto.setCreateDate(this.createDate);

        return taskDto;
    }

    public TaskEntity toEntity(UserEntity userEntity) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(this.title);
        taskEntity.setCreateDate(this.createDate);
        taskEntity.setUser(userEntity);

        return taskEntity;
    }
}
