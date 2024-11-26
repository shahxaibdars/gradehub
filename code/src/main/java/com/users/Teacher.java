package com.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseConnector;
import com.academic.Assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Teacher extends User implements UserInterface{
    private String dept;

    public Teacher(String id){
        super(id);
        initialize();
    }

    static boolean validate(String teacherId){
        DatabaseConnector db = DatabaseConnector.getInstance();

        try{
            String searchQuery = "SELECT userId from Faculty where userId = ? ";
            try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

                pstmt.setString(1, teacherId);
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (!resultSet.next()) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validateDpartment(String department){
        
        if (dept.equals(department)){
            return true;
        }
        return false;
    }

    public void initialize(){
        String searchQuery = "SELECT name, phoneNumber, department FROM user, faculty where faculty.userId = user.userId AND faculty.userId = ? ";
        DatabaseConnector db = DatabaseConnector.getInstance();
        try (Connection connection = db.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name =  resultSet.getString("name");
                    this.contact = resultSet.getString("phoneNumber");
                    this.dept = resultSet.getString("department");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> fetchCoursesFromDatabase() {
        ObservableList<String> courseList = FXCollections.observableArrayList();
        String query = "SELECT c.courseName FROM Course c " +
                       "JOIN FacultyCourse fc ON c.courseID = fc.courseID " +
                       "WHERE fc.facultyID = ?";
        DatabaseConnector db = DatabaseConnector.getInstance();

        try (Connection connection = db.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(resultSet.getString("courseName"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }

    public String saveMarksToDatabase(String course, String assessmentType, String studentId, String marks) {
        
        int number = Integer.parseInt(marks);

        String result = Assessment.validateMarks(number, assessmentType);
        if (!(result.equals("success"))){
            return result;
        }

        if (!Student.validate(studentId)){
            return "Student ID not found";
        }

        Student student = new Student(studentId);
        if (!student.validateDpartment(dept)){
            return "Student ID not found";
        }

        DatabaseConnector db = DatabaseConnector.getInstance();
        try{
            String searchQuery2 = "SELECT c.courseId FROM Course c JOIN StudentCourse sc ON c.courseID = sc.courseID WHERE sc.userID = ? AND c.courseName = ?";
            String courseId;
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(searchQuery2)) {

                pstmt.setString(1, studentId);
                pstmt.setString(2, course);
                
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        courseId = resultSet.getString("courseId");
                    }
                    else{
                        return "Student not registered in this Course";
                    }
                }
            }
            
            String assessmentId = courseId + "_" + assessmentType;

            String insertSQL2 = "INSERT INTO AssessmentMarks (assessmentID, userID, obtainedMarks) VALUES (?, ?, ?)";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {

                pstmt.setString(1, assessmentId);
                pstmt.setString(2, studentId);
                pstmt.setString(3, marks);
                
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return "Marks inserted successfully!";
                } else{
                    return "Marks Insertion Failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Database Connection Failed";
        }
    }

    public String saveAttendanceToDatabase(String course, String date, String studentId, String attendanceStatus) {
        // Simulate saving attendance to the database
        if (!Student.validate(studentId)){
            return "Student ID not found!";
        }

        Student student = new Student(studentId);
        if (!student.validateDpartment(dept)){
            return "Student ID not found!";
        }

        DatabaseConnector db = DatabaseConnector.getInstance();
        try{

            String searchQuery2 = "SELECT c.courseId FROM Course c JOIN StudentCourse sc ON c.courseID = sc.courseID WHERE sc.userID = ? AND c.courseName = ?";
            String courseId;
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(searchQuery2)) {

                pstmt.setString(1, studentId);
                pstmt.setString(2, course);
                
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        courseId = resultSet.getString("courseId");
                    }
                    else{
                        return "Student not registered in this course!";
                    }
                }
            }


            String insertSQL2 = "INSERT INTO AttendanceRecord (courseID, userID, date, status) VALUES (?, ?, ?, ?)";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {

                pstmt.setString(1, courseId);
                pstmt.setString(2, studentId);
                pstmt.setString(3, date);
                pstmt.setString(4, attendanceStatus);
                
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return "success";
                } else{
                    return "Error Uploading Attendance";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Database Connection Failed";
        }
    }
}
