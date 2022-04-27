package com.example.kamaz.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "position")
public class PositionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String title;
    @NonNull
    @OneToMany(
            mappedBy = "position",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<UserEntity> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionEntity)) return false;
        PositionEntity that = (PositionEntity) o;
        return id == that.id && title.equals(that.title) && userList.equals(that.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, userList);
    }
}

