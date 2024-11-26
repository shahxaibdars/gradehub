package com.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseConnector;
import com.academic.Course;

public class Admin {

    private static Admin instance;
    private Admin() {}

    public static  Admin getInstance() {
        if (instance == null) {  // Lazy initialization
            instance = new Admin();
        }
        return instance;
    }

    public static String validateCredentials(String userId,String password){
        String userType = null;
        DatabaseConnector db = DatabaseConnector.getInstance();
        try (Connection connection = db.connect()) {
            try {
                // Query for User
                String userQuery = "SELECT userType FROM User WHERE userID = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userType = resultSet.getString("userType");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userType;
    }

    public String saveStudentToDatabase(String id, String password, String name, String contact, String program, String enrolYear) {
        
        if (Student.validate(id)){
            return "ID already in use";
        }
        
        String email = id + "@gradehub.com";
        int rowsAffected = 0;
        DatabaseConnector db = DatabaseConnector.getInstance();
        try{
            String insertSQL = "INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES (?, ?, ?, ?, ?, ?)";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, id);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.setString(4, email);
                pstmt.setString(5, contact);
                pstmt.setString(6, "Student");

                rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in user table!");
                }
            }

            if (rowsAffected > 0){
                String insertSQL2 = "INSERT INTO Student (userID, batch, department, semester, cgpa) VALUES (?, ?, ?, ?, ?)";
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {

                    pstmt.setString(1, id);
                    pstmt.setString(2, enrolYear);
                    pstmt.setString(3, program);
                    pstmt.setString(4, "1");
                    pstmt.setString(5, "0");

                    rowsAffected = pstmt.executeUpdate();
        
                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully in student table!");
                    }
                }
            }
        } catch (Exception e) {
            return "ID already in USE";
        }
        return "success";
    }

    public String removeStudentFromDatabase(String studentId){

        if (!(Student.validate(studentId))){
            return "ID NOT FOUND!";
        }
        DatabaseConnector db = DatabaseConnector.getInstance();
        try{
            int rowsAffected = 0;
            String deleteQuery = "DELETE FROM Student WHERE userID = ?";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
  
                pstmt.setString(1, studentId);
  
                rowsAffected = pstmt.executeUpdate();
      
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from student table!");
                }
            }
              
            if (rowsAffected > 0){
                String deleteQuery2 = "DELETE FROM User WHERE userID = ?";
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery2)) {
                    pstmt.setString(1, studentId);
      
                    rowsAffected = pstmt.executeUpdate();
          
                    if (rowsAffected > 0) {
                        System.out.println("Data deleted successfully from user table!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Database Connection Failed";
        }
        return "success";
    }

    public String saveTeacherToDatabase(String id, String password, String name, String contact, String dept, String desg, String join) {
        
        if ((Teacher.validate(id))){
            return "ID already in USE";
        }
        
        String email = id + "@gradehub.com";
        int rowsAffected = 0;
        DatabaseConnector db = DatabaseConnector.getInstance();
        try{
            String insertSQL = "INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES (?, ?, ?, ?, ?, ?)";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, id);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.setString(4, email);
                pstmt.setString(5, contact);
                pstmt.setString(6, "Faculty");
  
                rowsAffected = pstmt.executeUpdate();
      
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in user table!");
                }
            }
  
            if (rowsAffected > 0){
                String insertSQL2 = "INSERT INTO Faculty (userID, department, designation, joinDate) VALUES (?, ?, ?, ?)";
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {
  
                    pstmt.setString(1, id);
                    pstmt.setString(2, dept);
                    pstmt.setString(3, desg);
                    pstmt.setString(4, join);
                      
                    rowsAffected = pstmt.executeUpdate();
  
                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully in faculty table!");
                    }
                }
            }
        } catch (Exception e) {
            return "ID already in USE";
        }
        return "success";
    }

    public String removeTeacherFromDatabase(String teacherId) {
        
        if (!(Teacher.validate(teacherId))){
            return "ID NOT FOUND!";
        }
        DatabaseConnector db = DatabaseConnector.getInstance();

        try{
            String deleteQuery = "DELETE FROM Faculty WHERE userID = ?";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
  
                pstmt.setString(1, teacherId);
  
                int rowsAffected = pstmt.executeUpdate();
      
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from faculty table!");
                }
            }
  
            String deleteQuery2 = "DELETE FROM User WHERE userID = ?";
            try(Connection conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery2)) {
  
                pstmt.setString(1, teacherId);
  
                int rowsAffected = pstmt.executeUpdate();
      
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from user table!");
                }
            }
              
        } catch (Exception e) {
            e.printStackTrace();
            return "Database Connection Failed";
        }
        return "success";
    }

    public String allocateCourse(String courseId, String teacherId){
        
        Course course = new Course(courseId);
        Teacher teacher = new Teacher(teacherId);

        if (!(course.validateCourse())){
            return "Course Id not found";
        } else if (!(Teacher.validate(teacherId))){
            return "Teacher Id not found";
        } else if (course.validateCourseTeacher()){
            return "This course is already allocated";
        }

        String dept = course.getCourseDepartment();        
        if (!(teacher.validateDpartment(dept))){
            return "This course is not available in your department";
        }
        DatabaseConnector db = DatabaseConnector.getInstance();

        try{
            String insertSQL = "INSERT INTO FacultyCourse (facultyId, courseId) VALUES (?, ?)";
            try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, teacherId);
                pstmt.setString(2, courseId);
  
                int rowsAffected = pstmt.executeUpdate();
      
                if (rowsAffected > 0) {
                    return "success";
                }
            }
        } catch (Exception e) {
            return "Database Connection Failed";
        }
        return "success";
    }

}
