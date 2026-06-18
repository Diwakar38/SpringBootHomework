package com.homework.CollegeManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @OneToMany
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    private Set<Student> students = new HashSet<>();
}
