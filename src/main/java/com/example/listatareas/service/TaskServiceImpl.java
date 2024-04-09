package com.example.listatareas.service;

import com.example.listatareas.models.Task;
import com.example.listatareas.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public ResponseEntity<List<Task>> getAll() {

        List<Task> tasks = repository.findAll();

        if (!tasks.isEmpty()){
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Task> createTask(@RequestBody Task newTask){
        try {
            Task response = repository.save(newTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Task> getById(@PathVariable Long id) {

        Optional<Task> task = repository.findById(id);

        if (task.isPresent()){
            return ResponseEntity.ok(task.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity updateTask(@RequestBody Task newData, @PathVariable Long id) {

        Optional<Task> optionalTask = repository.findById(id);
        if(!optionalTask.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Task currentTask = optionalTask.get();
        currentTask.setDescription(newData.getDescription());
        currentTask.setDateline(newData.getDateline());
        currentTask.setTag(newData.getTag());
        currentTask.setStatus(newData.getStatus());

        try {
            repository.save(currentTask);
            return ResponseEntity.noContent().build();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @Override
    public ResponseEntity deleteTask(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}