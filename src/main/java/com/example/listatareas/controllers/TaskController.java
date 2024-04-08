package com.example.listatareas.controllers;


import com.example.listatareas.models.Task;
import com.example.listatareas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/lista")
    public ResponseEntity<List<Task>> getAllController() { return taskService.getAll(); }

    @PostMapping("/nueva")
    public ResponseEntity<Task> createTaskController(@RequestBody Task newTask){
        return taskService.createTask(newTask);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Task> getByIdController(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity updateTaskController(@RequestBody Task newData, @PathVariable Long id) {
        return taskService.updateTask(newData, id);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteTaskController(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
