package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query("SELECT user FROM GroupEntity user WHERE user.id IN :ids ORDER BY user.id DESC")
    List<GroupEntity> findAllByIds(List<Integer> ids);
    GroupEntity getById(int id);
    void deleteById(int id);
}
