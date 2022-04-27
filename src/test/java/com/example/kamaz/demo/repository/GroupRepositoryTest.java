package com.example.kamaz.demo.repository;

import com.example.kamaz.demo.KamazDemoApplication;
import com.example.kamaz.demo.entity.GroupEntity;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KamazDemoApplication.class)
class GroupRepositoryTest {
    @Autowired
    GroupRepository groupRepository;

    @Test
    void testSaveAndGetSuccess() {
        String title = "Title";
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setTitle(title);

        GroupEntity savedGroup = groupRepository.save(groupEntity);

        assertThat(savedGroup.getTitle()).isEqualTo(title);
        assertThat(groupRepository.getById(savedGroup.getId()).getTitle()).isEqualTo(title);
    }

    @Test
    void testFindBuIdsSuccess() {
        String title1 = "Title1";
        GroupEntity groupEntity1 = new GroupEntity();
        groupEntity1.setTitle(title1);
        String title2 = "Title2";
        GroupEntity groupEntity2 = new GroupEntity();
        groupEntity2.setTitle(title2);

        GroupEntity savedGroup1 = groupRepository.save(groupEntity1);
        GroupEntity savedGroup2 = groupRepository.save(groupEntity2);

        List<GroupEntity> foundEntityList = groupRepository.findAllByIds(List.of(savedGroup1.getId(), savedGroup2.getId()));
        assertThat(foundEntityList.size()).isEqualTo(2);
    }
}