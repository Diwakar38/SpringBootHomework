-- ============================================
-- 1. PATIENTS (no dependencies)
-- ============================================
INSERT INTO patient (name, gender, birth_date, email, blood_group_type)
VALUES
    ('Aarav Sharma',    'MALE',   '1990-05-10', 'aarav.sharma@example.com',    'O_POSITIVE'),
    ('Diya Patel',      'FEMALE', '1995-08-20', 'diya.patel@example.com',      'A_POSITIVE'),
    ('Dishant Verma',   'MALE',   '1988-03-15', 'dishant.verma@example.com',   'A_POSITIVE'),
    ('Neha Iyer',       'FEMALE', '1992-12-01', 'neha.iyer@example.com',       'AB_POSITIVE'),
    ('Kabir Singh',     'MALE',   '1993-07-11', 'kabir.singh@example.com',     'O_POSITIVE'),
    ('Priya Nair',      'FEMALE', '1998-02-14', 'priya.nair@example.com',      'B_POSITIVE'),
    ('Rohan Gupta',     'MALE',   '1985-11-23', 'rohan.gupta@example.com',     'A_NEGATIVE'),
    ('Anjali Desai',    'FEMALE', '2000-06-30', 'anjali.desai@example.com',    'O_NEGATIVE'),
    ('Vikram Rao',      'MALE',   '1975-09-05', 'vikram.rao@example.com',      'AB_NEGATIVE'),
    ('Sneha Joshi',     'FEMALE', '1983-04-17', 'sneha.joshi@example.com',     'B_NEGATIVE'),
    ('Amit Tiwari',     'MALE',   '1996-08-08', 'amit.tiwari@example.com',     'O_POSITIVE'),
    ('Pooja Menon',     'FEMALE', '1991-01-25', 'pooja.menon@example.com',     'A_POSITIVE'),
    ('Karan Malhotra',  'MALE',   '1987-12-19', 'karan.malhotra@example.com',  'B_POSITIVE'),
    ('Ritika Saxena',   'FEMALE', '1994-03-11', 'ritika.saxena@example.com',   'AB_POSITIVE'),
    ('Suraj Pillai',    'MALE',   '1979-07-22', 'suraj.pillai@example.com',    'O_POSITIVE');

-- ============================================
-- 2. DOCTORS (no dependencies)
-- ============================================
INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta',    'Cardiology',       'rakesh.mehta@example.com'),     -- id: 1
    ('Dr. Rakesh Madasi',   'Cardiology',       'rakesh.madasi@example.com'),    -- id: 2
    ('Dr. Suresh Champ',    'Cardiology',       'suresh.champ@example.com'),     -- id: 3
    ('Dr. Sneha Kapoor',    'Dermatology',      'sneha.kapoor@example.com'),     -- id: 4
    ('Dr. Arjun Nair',      'Orthopedics',      'arjun.nair@example.com'),       -- id: 5
    ('Dr. Priya Sharma',    'Neurology',        'priya.sharma@example.com'),     -- id: 6
    ('Dr. Anil Verma',      'Neurology',        'anil.verma@example.com'),       -- id: 7
    ('Dr. Kavita Rao',      'Pediatrics',       'kavita.rao@example.com'),       -- id: 8
    ('Dr. Ramesh Iyer',     'Pediatrics',       'ramesh.iyer@example.com'),      -- id: 9
    ('Dr. Meena Joshi',     'Gynecology',       'meena.joshi@example.com'),      -- id: 10
    ('Dr. Sanjay Gupta',    'Orthopedics',      'sanjay.gupta@example.com'),     -- id: 11
    ('Dr. Divya Menon',     'Dermatology',      'divya.menon@example.com'),      -- id: 12
    ('Dr. Harish Tiwari',   'General Medicine', 'harish.tiwari@example.com'),    -- id: 13
    ('Dr. Lakshmi Pillai',  'General Medicine', 'lakshmi.pillai@example.com'),   -- id: 14
    ('Dr. Nikhil Saxena',   'Gynecology',       'nikhil.saxena@example.com');    -- id: 15

-- ============================================
-- 3. DEPARTMENTS (depends on doctors)
-- ============================================
INSERT INTO department (name, head_doctor_id)
VALUES
    ('Cardiology',          1),    -- Head: Dr. Rakesh Mehta
    ('Dermatology',         4),    -- Head: Dr. Sneha Kapoor
    ('Orthopedics',         5),    -- Head: Dr. Arjun Nair
    ('Neurology',           6),    -- Head: Dr. Priya Sharma
    ('Pediatrics',          8),    -- Head: Dr. Kavita Rao
    ('Gynecology',          10),   -- Head: Dr. Meena Joshi
    ('General Medicine',    13);   -- Head: Dr. Harish Tiwari

-- ============================================
-- 4. DEPARTMENT_DOCTORS join table
--    (depends on both doctors and departments)
-- ============================================

-- Cardiology (dept 1)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3);

-- Dermatology (dept 2)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (2, 4),
    (2, 12);

-- Orthopedics (dept 3)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (3, 5),
    (3, 11);

-- Neurology (dept 4)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (4, 6),
    (4, 7);

-- Pediatrics (dept 5)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (5, 8),
    (5, 9);

-- Gynecology (dept 6)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (6, 10),
    (6, 15);

-- General Medicine (dept 7)
INSERT INTO department_doctors (department_id, doctors_id) VALUES
    (7, 13),
    (7, 14);