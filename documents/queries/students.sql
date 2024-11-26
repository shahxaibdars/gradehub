-- Insert Admin for CS Program
INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES
('0', 'pass', 'Shahzaib', 'shahzaib13082@gmail.com', '1212121212', 'Admin');
INSERT INTO Admin (userID) values (0);

-- Insert Students for CS Program
INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES
-- CS Program, Semester 1, Batch 2024
('CS101', 'pass', 'Alice Smith', 'alice.smith@cs.com', '1111111111', 'Student'),
('CS102', 'pass', 'Bob Johnson', 'bob.johnson@cs.com', '1111111112', 'Student'),
('CS103', 'pass', 'Charlie Brown', 'charlie.brown@cs.com', '1111111113', 'Student'),
('CS104', 'pass', 'Diana Miller', 'diana.miller@cs.com', '1111111114', 'Student'),
('CS105', 'pass', 'Ethan Davis', 'ethan.davis@cs.com', '1111111115', 'Student'),
-- CS Program, Semester 2, Batch 2024
('CS201', 'pass', 'Fiona Gray', 'fiona.gray@cs.com', '1111111121', 'Student'),
('CS202', 'pass', 'George White', 'george.white@cs.com', '1111111122', 'Student'),
('CS203', 'pass', 'Hannah Lee', 'hannah.lee@cs.com', '1111111123', 'Student'),
('CS204', 'pass', 'Isaac Green', 'isaac.green@cs.com', '1111111124', 'Student'),
('CS205', 'pass', 'Jack Harris', 'jack.harris@cs.com', '1111111125', 'Student'),
-- CS Program, Semester 3, Batch 2023
('CS301', 'pass', 'Katie Adams', 'katie.adams@cs.com', '1111111131', 'Student'),
('CS302', 'pass', 'Liam Carter', 'liam.carter@cs.com', '1111111132', 'Student'),
('CS303', 'pass', 'Mia Scott', 'mia.scott@cs.com', '1111111133', 'Student'),
('CS304', 'pass', 'Nathan Evans', 'nathan.evans@cs.com', '1111111134', 'Student'),
('CS305', 'pass', 'Olivia Brown', 'olivia.brown@cs.com', '1111111135', 'Student'),
-- CS Program, Semester 4, Batch 2023
('CS401', 'pass', 'Peter King', 'peter.king@cs.com', '1111111141', 'Student'),
('CS402', 'pass', 'Quinn Reed', 'quinn.reed@cs.com', '1111111142', 'Student'),
('CS403', 'pass', 'Rachel Young', 'rachel.young@cs.com', '1111111143', 'Student'),
('CS404', 'pass', 'Samuel Walker', 'samuel.walker@cs.com', '1111111144', 'Student'),
('CS405', 'pass', 'Tina Hall', 'tina.hall@cs.com', '1111111145', 'Student'),
-- CS Program, Semester 5, Batch 2022
('CS501', 'pass', 'Umar Hill', 'umar.hill@cs.com', '1111111151', 'Student'),
('CS502', 'pass', 'Victoria Adams', 'victoria.adams@cs.com', '1111111152', 'Student'),
('CS503', 'pass', 'William Baker', 'william.baker@cs.com', '1111111153', 'Student'),
('CS504', 'pass', 'Xander Hayes', 'xander.hayes@cs.com', '1111111154', 'Student'),
('CS505', 'pass', 'Yara Scott', 'yara.scott@cs.com', '1111111155', 'Student'),
-- CS Program, Semester 6, Batch 2022
('CS601', 'pass', 'Zara Price', 'zara.price@cs.com', '1111111161', 'Student'),
('CS602', 'pass', 'Adam Knight', 'adam.knight@cs.com', '1111111162', 'Student'),
('CS603', 'pass', 'Bella Moore', 'bella.moore@cs.com', '1111111163', 'Student'),
('CS604', 'pass', 'Cameron Lee', 'cameron.lee@cs.com', '1111111164', 'Student'),
('CS605', 'pass', 'Diana King', 'diana.king@cs.com', '1111111165', 'Student'),
-- CS Program, Semester 7, Batch 2021
('CS701', 'pass', 'Evan Turner', 'evan.turner@cs.com', '1111111171', 'Student'),
('CS702', 'pass', 'Fiona Price', 'fiona.price@cs.com', '1111111172', 'Student'),
('CS703', 'pass', 'George Harris', 'george.harris@cs.com', '1111111173', 'Student'),
('CS704', 'pass', 'Hannah Young', 'hannah.young@cs.com', '1111111174', 'Student'),
('CS705', 'pass', 'Isaac Hall', 'isaac.hall@cs.com', '1111111175', 'Student'),
-- CS Program, Semester 8, Batch 2021
('CS801', 'pass', 'Jack Lee', 'jack.lee@cs.com', '1111111181', 'Student'),
('CS802', 'pass', 'Katie Hill', 'katie.hill@cs.com', '1111111182', 'Student'),
('CS803', 'pass', 'Liam Moore', 'liam.moore@cs.com', '1111111183', 'Student'),
('CS804', 'pass', 'Mian Scott', 'mian.scott@cs.com', '1111111184', 'Student'),
('CS805', 'pass', 'Nathan Price', 'nathan.price@cs.com', '1111111185', 'Student');

-- Insert into Student Table for CS Program
INSERT INTO Student (userID, batch, department, semester, cgpa) VALUES
-- Semester 1, CGPA = 0
('CS101', 24, 'CS', 1, 0),
('CS102', 24, 'CS', 1, 0),
('CS103', 24, 'CS', 1, 0),
('CS104', 24, 'CS', 1, 0),
('CS105', 24, 'CS', 1, 0),
-- Semester 2
('CS201', 24, 'CS', 2, 3.5),
('CS202', 24, 'CS', 2, 3.6),
('CS203', 24, 'CS', 2, 2.5),
('CS204', 24, 'CS', 2, 2.6),
('CS205', 24, 'CS', 2, 3.3),
-- Semester 3
('CS301', 23, 'CS', 3, 3.5),
('CS302', 23, 'CS', 3, 3.6),
('CS303', 23, 'CS', 3, 2.5),
('CS304', 23, 'CS', 3, 2.6),
('CS305', 23, 'CS', 3, 3.3),
-- Semester 4
('CS401', 23, 'CS', 4, 3.5),
('CS402', 23, 'CS', 4, 3.6),
('CS403', 23, 'CS', 4, 2.5),
('CS404', 23, 'CS', 4, 2.6),
('CS405', 23, 'CS', 4, 3.3),
-- Semester 5
('CS501', 22, 'CS', 5, 3.5),
('CS502', 22, 'CS', 5, 3.6),
('CS503', 22, 'CS', 5, 2.5),
('CS504', 22, 'CS', 5, 2.6),
('CS505', 22, 'CS', 5, 3.3),
-- Semester 6
('CS601', 22, 'CS', 6, 3.5),
('CS602', 22, 'CS', 6, 3.6),
('CS603', 22, 'CS', 6, 2.5),
('CS604', 22, 'CS', 6, 2.6),
('CS605', 22, 'CS', 6, 3.3),
-- Semester 2
('CS701', 21, 'CS', 7, 3.5),
('CS702', 21, 'CS', 7, 3.6),
('CS703', 21, 'CS', 7, 2.5),
('CS704', 21, 'CS', 7, 2.6),
('CS705', 21, 'CS', 7, 3.3),
-- Semester 2
('CS801', 21, 'CS', 8, 3.5),
('CS802', 21, 'CS', 8, 3.6),
('CS803', 21, 'CS', 8, 2.5),
('CS804', 21, 'CS', 8, 2.6),
('CS805', 21, 'CS', 8, 3.3);

-- Repeat the same for AI and SE programs.