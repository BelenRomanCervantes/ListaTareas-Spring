package com.example.listatareas.controllers;

import com.example.listatareas.models.User;
import com.example.listatareas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping("/lista")
    public ResponseEntity<List<User>> getAllController() { return userService.getAll(); }

    @PostMapping("/nuevo")
    public ResponseEntity<User> createUserController(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<User> getByIdController(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity updateUserController(@RequestBody User newData, @PathVariable Long id) {
        return userService.updateUser(newData, id);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity deleteUserController(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}




