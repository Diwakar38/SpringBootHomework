package com.homework.CollegeManagementSystem;

import com.homework.CollegeManagementSystem.entities.Professor;
import com.homework.CollegeManagementSystem.entities.Student;
import com.homework.CollegeManagementSystem.entities.Subject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Student entity tests")
class StudentTest {

    @Autowired
    private Validator validator;

    // ----------------------------------------------------------------
    // Construction & defaults
    // ----------------------------------------------------------------

    @Test
    @DisplayName("No-args constructor creates empty collections")
    void noArgsConstructor_setsEmptyCollections() {
        Student student = new Student();
        assertThat(student.getProfessors()).isNotNull().isEmpty();
        assertThat(student.getSubjects()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("All-args constructor sets all fields")
    void allArgsConstructor_setsAllFields() {
        Student student = new Student(1L, "Alice Johnson", new HashSet<>(), new HashSet<>());
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("Alice Johnson");
    }

    // ----------------------------------------------------------------
    // Getters / Setters
    // ----------------------------------------------------------------

    @Test
    @DisplayName("setName / getName round-trip")
    void setAndGetName() {
        Student student = new Student();
        student.setName("Bob Smith");
        assertThat(student.getName()).isEqualTo("Bob Smith");
    }

    @Test
    @DisplayName("setId / getId round-trip")
    void setAndGetId() {
        Student student = new Student();
        student.setId(42L);
        assertThat(student.getId()).isEqualTo(42L);
    }

    // ----------------------------------------------------------------
    // Validation
    // ----------------------------------------------------------------

    @Test
    @DisplayName("Valid name passes validation")
    void validName_noViolations() {
        Student student = new Student();
        student.setName("Carol White");
        student.setProfessors(new HashSet<>());
        student.setSubjects(new HashSet<>());

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        assertThat(violations).isEmpty();
    }

    // ----------------------------------------------------------------
    // Relationships
    // ----------------------------------------------------------------

    @Test
    @DisplayName("Adding a professor to the set is reflected in the collection")
    void addProfessor_appearsInSet() {
        Student student = new Student();
        Professor prof = new Professor();
        prof.setId(1L);
        prof.setTitle("Dr. Feynman");

        student.professors.add(prof);

        assertThat(student.professors).hasSize(1).contains(prof);
    }

    @Test
    @DisplayName("Adding a subject to the set is reflected in the collection")
    void addSubject_appearsInSet() {
        Student student = new Student();
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setTitle("Physics");

        student.subjects.add(subject);

        assertThat(student.subjects).hasSize(1).contains(subject);
    }

    @Test
    @DisplayName("Student can be associated with multiple professors")
    void multipleAssociations_professors() {
        Student student = new Student();
        for (int i = 1; i <= 5; i++) {
            Professor p = new Professor();
            p.setId((long) i);
            p.setTitle("Prof " + i);
            student.professors.add(p);
        }
        assertThat(student.professors).hasSize(5);
    }

    @Test
    @DisplayName("Student can be associated with multiple subjects")
    void multipleAssociations_subjects() {
        Student student = new Student();
        for (int i = 1; i <= 4; i++) {
            Subject s = new Subject();
            s.setId((long) i);
            s.setTitle("Subject " + i);
            student.subjects.add(s);
        }
        assertThat(student.subjects).hasSize(4);
    }

    @Test
    @DisplayName("Duplicate professor is not added to the HashSet")
    void duplicateProfessor_notAdded() {
        Student student = new Student();
        Professor prof = new Professor();
        prof.setId(1L);

        student.professors.add(prof);
        student.professors.add(prof);

        assertThat(student.professors).hasSize(1);
    }
}