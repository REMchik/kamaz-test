package com.example.kamaz.demo.controller;

import com.example.kamaz.demo.dto.GroupDto;
import com.example.kamaz.demo.model.Group;
import com.example.kamaz.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kamaz/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupDto> getList() {
        return groupService.getList()
                .stream().map(Group::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public int create(@RequestBody GroupDto groupDto) {
        return groupService.create(groupDto.toModel());
    }

    @PatchMapping("/{groupId}/user/{userId}")
    public void addUser(@PathVariable int groupId, @PathVariable int userId) {
        groupService.addUser(groupId, userId);
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable int groupId) {
        groupService.delete(groupId);
    }
}

