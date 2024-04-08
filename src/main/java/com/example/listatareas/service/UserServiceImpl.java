package com.example.listatareas.service;

import com.example.listatareas.models.Task;
import com.example.listatareas.models.User;
import com.example.listatareas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<List<User>> getAll() {

        List<User> users = userRepository.findAll();

        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            User response = userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity updateUser(@RequestBody User newData, @PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }

        User currentUser = optionalUser.get();
        currentUser.setName(newData.getName());
        currentUser.setLastname(newData.getLastname());
        currentUser.setBirthdate(newData.getBirthdate());
        currentUser.setUsername(newData.getUsername());
        currentUser.setEmail(newData.getEmail());

        try {
            currentUser.setPassword(passwordEncoder.encode(newData.getPassword()));
            userRepository.save(currentUser);
            return ResponseEntity.noContent().build();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
