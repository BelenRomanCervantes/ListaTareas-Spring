package com.example.listatareas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate dateline;

    private String tag;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    public Task() {

    }
}
