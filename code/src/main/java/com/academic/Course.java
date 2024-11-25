package com.academic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseConnector;

public class Course {

     String courseId;

     public Course(String courseId){
          this.courseId = courseId;
     }

     public boolean validateCourse(){

          String searchQuery = "SELECT courseId from Course where courseId = ? ";
          DatabaseConnector db = DatabaseConnector.getInstance();
          try{
               try(Connection conn = db.connect();
               PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, courseId);
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
     
     public String getCourseDepartment(){
          String searchQuery = "SELECT department from Course where courseId = ? ";
          String result = null;
          DatabaseConnector db = DatabaseConnector.getInstance();
          try{
               try(Connection conn = db.connect();
               PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, courseId);
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                         if (resultSet.next()) {
                              result = resultSet.getString("department");
                         }
                    }
               }
          } catch (Exception e) {
               e.printStackTrace();
               return result;
          }

          return result;
     }

     public boolean validateCourseTeacher(){
          String searchQuery = "SELECT courseId from FacultyCourse where courseId = ? ";
          DatabaseConnector db = DatabaseConnector.getInstance();
          try{
               try(Connection conn = db.connect();
               PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                    pstmt.setString(1, courseId);
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
}