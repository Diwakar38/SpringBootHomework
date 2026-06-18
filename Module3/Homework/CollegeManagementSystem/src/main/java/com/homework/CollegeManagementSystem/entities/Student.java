package com.homework.CollegeManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany
    public Set<Professor> professors = new HashSet<>();

    @ManyToMany
    public Set<Subject> subjects = new HashSet<>();
}
