package com.example.kamaz.demo.dto;

import com.example.kamaz.demo.model.Task;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto implements Serializable {

    @NonNull
    private String title;
    private LocalDateTime createDate;

    public Task toModel(int userId) {
        Task task = new Task();
        task.setTitle(this.title);
        task.setCreateDate(this.createDate);
        task.setUserId(userId);

        return task;
    }
}
