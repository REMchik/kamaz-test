package com.example.kamaz.demo.model;

import com.example.kamaz.demo.dto.TaskDto;
import com.example.kamaz.demo.entity.TaskEntity;
import com.example.kamaz.demo.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Task {

    private int id;
    @NonNull
    private String title;
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
        taskEntity.setUser(userEntity);
        if (this.getCreateDate() != null) {
            taskEntity.setCreateDate(this.createDate);
        }

        return taskEntity;
    }
}
