package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetListOfTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = List.of(new TaskDto(1L, "Test Title", "Test Content"));

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");
        when(dbService.getTask(task.getId())).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test Content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given, When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Name", "Description");
        TaskDto updatedTaskDto = new TaskDto(1L, "Test Name", "Test Description");
        Task savedTask = dbService.saveTask(task);
        when(taskMapper.mapToTaskDto(savedTask)).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test Description")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task createdTask = new Task(1L, "Name", "Description");
        TaskDto createdTaskDto = new TaskDto(1L, "Test Name", "Test Description");
        when(dbService.saveTask(taskMapper.mapToTask(createdTaskDto))).thenReturn(createdTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTaskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                        .andExpect(status().is(200));
        Mockito.verify(dbService, times(1)).saveTask(taskMapper.mapToTask(createdTaskDto));
    }
}
