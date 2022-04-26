package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getById(int userId);
    void deleteById(int id);
}
