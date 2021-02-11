package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List <TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping("getTask")
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1, "test title", "test_content");
    }

    @DeleteMapping("deleteTask")
    public void deleteTask (Long taskId) {

    }

    @PutMapping("updateTask")
    public TaskDto updateTask(TaskDto task) {
        return new TaskDto(1l, "Edited test title", "Test Content");
    }

    @PostMapping("createTask")
    public void createTask (TaskDto task) {

    }
}
