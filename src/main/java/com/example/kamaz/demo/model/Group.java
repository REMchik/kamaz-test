package com.example.kamaz.demo.model;

import com.example.kamaz.demo.dto.GroupDto;
import com.example.kamaz.demo.entity.GroupEntity;
import com.example.kamaz.demo.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class Group {

    private int id;
    @NonNull
    private String title;
    private LocalDateTime  dateOfEmployment;

    Set<UserEntity> users;

    public GroupDto toDto() {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(this.id);
        groupDto.setTitle(this.title);
        groupDto.setDateOfEmployment(this.dateOfEmployment);
        groupDto.setUsers(this.users);

        return groupDto;
    }

    public GroupEntity toEntity() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(this.id);
        groupEntity.setTitle(this.title);
        if (this.getDateOfEmployment() != null) {
            groupEntity.setDateOfEmployment(this.dateOfEmployment);
        }

        return groupEntity;
    }
}
