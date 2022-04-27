package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.KamazDemoApplication;
import com.example.kamaz.demo.entity.PositionEntity;
import com.example.kamaz.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KamazDemoApplication.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void testSaveAndGetSuccess() {
        String name = "Andrey";
        int age = 33;
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setAge(age);
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setId(2);
        positionEntity.setTitle("Менеджер");
        userEntity.setPosition(positionEntity);

        UserEntity savedUser = userRepository.save(userEntity);

        assertThat(savedUser.getName()).isEqualTo(name);
        assertThat(userRepository.getById(savedUser.getId()).getName()).isEqualTo(name);
    }
}