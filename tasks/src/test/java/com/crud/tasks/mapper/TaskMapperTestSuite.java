package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, mappedTask.getId());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Test title", "test content");

        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("test content", mappedTaskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List <TaskDto> mapToTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, mapToTaskDtoList.size());
    }
}
