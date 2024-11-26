DROP DATABASE IF EXISTS gradehub;
CREATE DATABASE gradehub;
use gradehub;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS AttendanceRecord;
DROP TABLE IF EXISTS Assessment;
DROP TABLE IF EXISTS FacultyCourse;
DROP TABLE IF EXISTS StudentCourse;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Faculty;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS User;

SET FOREIGN_KEY_CHECKS = 1;

-- Table: User (This is the common table for all users)
CREATE TABLE User (
    userID VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber VARCHAR(20),
    userType ENUM('Student', 'Admin', 'Faculty') NOT NULL
);

-- Table: Student (Now using userID as the primary key)
CREATE TABLE Student (
    userID VARCHAR(50) PRIMARY KEY,
    batch INT NOT NULL,
    department ENUM('CS', 'SE', 'AI'),
    semester INT,
    cgpa DOUBLE DEFAULT 0,
    FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE
);

-- Table: Admin (Now using userID as the primary key)
CREATE TABLE Admin (
    userID VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE
);

-- Table: Faculty (Now using userID as the primary key)
CREATE TABLE Faculty (
    userID VARCHAR(50) PRIMARY KEY,
    department ENUM('CS', 'SE', 'AI'),
    designation ENUM('Assistant Professor', 'Associate Professor', 'Lecturer', 'Professor'),
    joinDate DATE,
    FOREIGN KEY (userID) REFERENCES User(userID) ON DELETE CASCADE
);

-- Table: Course (Faculty reference is now a userID)
CREATE TABLE Course (
    courseID VARCHAR(50) PRIMARY KEY,
    courseName VARCHAR(100) NOT NULL,
    creditHours INT NOT NULL,
    department ENUM('CS', 'SE', 'AI'),
    semester INT
);

-- Table: Assessment
CREATE TABLE Assessment (
    assessmentID VARCHAR(50) PRIMARY KEY,
    courseID VARCHAR(50),
    type ENUM('QUIZ', 'ASSIGNMENT', 'MIDTERM', 'FINAL'),
    totalMarks DOUBLE,
    dueDate DATE,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE
);

-- Table: AttendanceRecord
CREATE TABLE AttendanceRecord (
    attendanceID INT AUTO_INCREMENT PRIMARY KEY,
    courseID VARCHAR(50),
    date DATE NOT NULL,
    userID VARCHAR(50),
    status ENUM('PRESENT', 'ABSENT', 'LATE', 'LEAVE'),
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES Student(userID) ON DELETE CASCADE
);

-- Table: StudentCourse (Many-to-Many relationship between Student and Course)
CREATE TABLE StudentCourse (
    userID VARCHAR(50),
    courseID VARCHAR(50),
    PRIMARY KEY (userID, courseID),
    FOREIGN KEY (userID) REFERENCES Student(userID) ON DELETE CASCADE,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE
);

CREATE TABLE FacultyCourse (
    facultyID VARCHAR(50),
    courseID VARCHAR(50) UNIQUE,
    PRIMARY KEY (facultyID, courseID),
    FOREIGN KEY (facultyID) REFERENCES Faculty(userID) ON DELETE CASCADE,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE CASCADE
);
-- Table: AssessmentMarks
CREATE TABLE AssessmentMarks (
    assessmentID VARCHAR(50),
    userID VARCHAR(50),
    obtainedMarks DOUBLE NOT NULL,
    PRIMARY KEY (assessmentID, userID),
    FOREIGN KEY (assessmentID) REFERENCES Assessment(assessmentID) ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES Student(userID) ON DELETE CASCADE
);