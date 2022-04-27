package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.KamazDemoApplication;
import com.example.kamaz.demo.entity.PositionEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KamazDemoApplication.class)
class PositionRepositoryTest {

    @Autowired
    PositionRepository positionRepository;

    @Test
    void testFindByTitle() {
        String title = "Конструктор";

        PositionEntity positionEntity = positionRepository.findByTitle(title);

        assertNotNull(positionEntity);
        assertThat(positionEntity.getTitle()).isEqualTo(title);
    }
}