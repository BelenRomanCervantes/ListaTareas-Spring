package com.example.listatareas.service;

import com.example.listatareas.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {


    ResponseEntity<List<Task>> getAll();

    ResponseEntity<Task> createTask(@RequestBody Task newTask);

    ResponseEntity<Task> getById(@PathVariable Long id);

    ResponseEntity updateTask(@RequestBody Task newData, @PathVariable Long id);

    ResponseEntity deleteTask(Long id);
}
