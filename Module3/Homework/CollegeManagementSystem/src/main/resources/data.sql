-- ============================================================
-- College Management System — Dummy Data
-- ============================================================
-- HOW TO FIND YOUR ACTUAL TABLE/COLUMN NAMES:
--   Set spring.jpa.show-sql=true and spring.jpa.hibernate.ddl-auto=create
--   then check the CREATE TABLE statements in your startup logs.
-- ============================================================

-- ============================================================
-- 1. STUDENTS
-- ============================================================
INSERT INTO student (id, name) VALUES
(1,  'Alice Johnson'),
(2,  'Bob Smith'),
(3,  'Carol White'),
(4,  'David Brown'),
(5,  'Eva Martinez'),
(6,  'Frank Lee'),
(7,  'Grace Kim'),
(8,  'Henry Wilson'),
(9,  'Isla Davis'),
(10, 'Jack Thompson');

-- ============================================================
-- 2. PROFESSORS
-- ============================================================
INSERT INTO professor (id, title) VALUES
(1, 'Dr. Richard Feynman'),
(2, 'Prof. Ada Lovelace'),
(3, 'Dr. Alan Turing'),
(4, 'Prof. Marie Curie'),
(5, 'Dr. John Nash');

-- ============================================================
-- 3. SUBJECTS
-- ============================================================
INSERT INTO subject (id, title, professor_id) VALUES
(1,  'Introduction to Physics',   1),
(2,  'Quantum Mechanics',         1),
(3,  'Data Structures',           2),
(4,  'Algorithms and Complexity', 2),
(5,  'Theory of Computation',     3),
(6,  'Artificial Intelligence',   3),
(7,  'Organic Chemistry',         4),
(8,  'Nuclear Physics',           4),
(9,  'Game Theory',               5),
(10, 'Linear Programming',        5);

-- ============================================================
-- 4. PROFESSOR <-> STUDENT  (ManyToMany)
-- Hibernate default columns: professor_id, students_id
-- Table name: professor_students  (owner = Professor.students field)
-- ============================================================
INSERT INTO professor_students (professor_id, students_id) VALUES
-- Dr. Feynman → students 1,2,3,4
(1, 1), (1, 2), (1, 3), (1, 4),
-- Prof. Lovelace → students 2,3,5,6,7
(2, 2), (2, 3), (2, 5), (2, 6), (2, 7),
-- Dr. Turing → students 1,4,6,8
(3, 1), (3, 4), (3, 6), (3, 8),
-- Prof. Curie → students 3,7,9,10
(4, 3), (4, 7), (4, 9), (4, 10),
-- Dr. Nash → students 5,8,9,10
(5, 5), (5, 8), (5, 9), (5, 10);

-- ============================================================
-- 5. STUDENT <-> SUBJECT  (ManyToMany)
-- Hibernate default columns: student_id, subjects_id
-- Table name: student_subjects  (owner = Student.subjects field)
-- ============================================================
INSERT INTO student_subjects (student_id, subjects_id) VALUES
-- Alice  → Physics, Algorithms, AI
(1, 1), (1, 4), (1, 6),
-- Bob    → Physics, Quantum, Data Structures
(2, 1), (2, 2), (2, 3),
-- Carol  → Organic Chemistry, Nuclear Physics, Data Structures
(3, 7), (3, 8), (3, 3),
-- David  → Theory of Computation, AI, Game Theory
(4, 5), (4, 6), (4, 9),
-- Eva    → Algorithms, Game Theory, Linear Programming
(5, 4), (5, 9), (5, 10),
-- Frank  → Data Structures, AI, Algorithms
(6, 3), (6, 6), (6, 4),
-- Grace  → Organic Chemistry, Nuclear Physics, Quantum
(7, 7), (7, 8), (7, 2),
-- Henry  → Theory of Computation, Linear Programming, AI
(8, 5), (8, 10), (8, 6),
-- Isla   → Game Theory, Linear Programming, Organic Chemistry
(9, 9), (9, 10), (9, 7),
-- Jack   → Nuclear Physics, Quantum, Game Theory, Linear Programming
(10, 8), (10, 2), (10, 9), (10, 10);

-- ============================================================
-- 6. SUBJECT <-> STUDENT on Subject side
-- Hibernate default columns: subject_id, students_id
-- Table name: subject_students  (owner = Subject.students field)
-- ============================================================
INSERT INTO subject_students (subject_id, students_id) VALUES
(1, 1), (1, 2),
(2, 2), (2, 7), (2, 10),
(3, 2), (3, 3), (3, 6),
(4, 1), (4, 5), (4, 6),
(5, 4), (5, 8),
(6, 1), (6, 4), (6, 6), (6, 8),
(7, 3), (7, 7), (7, 9),
(8, 3), (8, 7), (8, 10),
(9, 4), (9, 5), (9, 9), (9, 10),
(10, 5), (10, 8), (10, 9), (10, 10);

-- ============================================================
-- 7. ADMISSION RECORDS  (One per student, fees > 0)
-- ============================================================
INSERT INTO admission_record (id, fees, student_id) VALUES
(1,  75000, 1),
(2,  82000, 2),
(3,  68000, 3),
(4,  91000, 4),
(5,  55000, 5),
(6,  78000, 6),
(7,  63000, 7),
(8,  87000, 8),
(9,  72000, 9),
(10, 95000, 10);