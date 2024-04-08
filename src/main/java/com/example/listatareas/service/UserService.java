package com.example.listatareas.service;

import com.example.listatareas.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {


    ResponseEntity<List<User>> getAll();

    ResponseEntity<User> createUser(@RequestBody User newUser);

    ResponseEntity<User> getById(@PathVariable Long id);

    ResponseEntity updateUser(@RequestBody User newData, @PathVariable Long id);

    ResponseEntity deleteUser(Long id);
}
