package com.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.database.DatabaseConnector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends User implements UserInterface{

    private String program;
    private String semester;

    public Student(String id){
        super(id);
        initialize();
    }

    static boolean validate(String studentId){
        DatabaseConnector db = DatabaseConnector.getInstance();
        try{
            String searchQuery = "SELECT userId from Student where userId = ? ";
            try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

                pstmt.setString(1, studentId);
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

    public boolean validateDpartment(String dept){
        if (program.equals(dept)){
            return true;
        }
        return false;
    }

    public void initialize(){
        String searchQuery = "SELECT name, phoneNumber, department, semester FROM user, student where student.userId = user.userId AND student.userId = ? ";
        DatabaseConnector db = DatabaseConnector.getInstance();
        try (Connection connection = db.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.name =  resultSet.getString("name");
                    this.contact = resultSet.getString("phoneNumber");
                    this.program = resultSet.getString("department");
                    this.semester = resultSet.getString("semester");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<String> fetchCoursesFromDatabase() {
        DatabaseConnector db = DatabaseConnector.getInstance();
        ObservableList<String> courseList = FXCollections.observableArrayList();
        String query = "SELECT c.courseName FROM Course c " +
                       "JOIN StudentCourse sc ON c.courseID = sc.courseID " +
                       "WHERE sc.userID = ?";

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

    public String fetchMarks(String course, String testType) {
        String query = "SELECT am.obtainedMarks, a.totalMarks " +
                       "FROM Assessment a " +
                       "JOIN Course c ON a.courseID = c.courseID " +
                       "JOIN StudentCourse sc ON c.courseID = sc.courseID " +
                       "JOIN AssessmentMarks am ON a.assessmentID = am.assessmentID " +
                       "WHERE sc.userID = ? AND c.courseName = ? AND a.type = ?";
        DatabaseConnector db = DatabaseConnector.getInstance();

        try (Connection connection = db.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, course);
            preparedStatement.setString(3, testType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String courseList = "";
                if (resultSet.next()) {
                    courseList = resultSet.getString("obtainedMarks");
                    courseList = courseList + "/" + resultSet.getString("totalMarks");
                }
                while (resultSet.next()) {
                    courseList = courseList + ", " + (resultSet.getString("obtainedMarks"));
                    courseList = courseList + "/" + resultSet.getString("totalMarks");
                }
                return courseList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String fetchAttendancePercentage(String course) {
        String attendencePercentage = "";
        System.out.println("Fetching attendance percentage for course: " + course);
        String query = "SELECT ((SUM(status = 'LATE') + SUM(status = 'PRESENT')) * 100.0 / COUNT(*)) AS result" +
                       " FROM AttendanceRecord" + 
                       " WHERE courseId = (SELECT CourseId from Course where courseName = ? ) AND userId = ? ;";
        DatabaseConnector db = DatabaseConnector.getInstance();
        
        try (Connection connection = db.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course);
            preparedStatement.setString(2, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    attendencePercentage =  resultSet.getString("result");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendencePercentage;
    }

    public String fetchAttendanceDetails(String course) {
        System.out.println("Fetching attendance details for course: " + course);
        String attendenceDetails = "";
        String query = "SELECT date, status " +
                       " FROM AttendanceRecord" + 
                       " WHERE courseId = (SELECT CourseId from Course where courseName = ? ) AND userId = ? ;";

        DatabaseConnector db = DatabaseConnector.getInstance();
        try (Connection connection = db.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course);
            preparedStatement.setString(2, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    attendenceDetails = "Date: " + resultSet.getString("date");
                    attendenceDetails = attendenceDetails + " - " + resultSet.getString("status") + "\n";
                }

                while (resultSet.next()) {
                    attendenceDetails = attendenceDetails + "Date: " + resultSet.getString("date");
                    attendenceDetails = attendenceDetails + " - " + resultSet.getString("status") + "\n";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendenceDetails;
    }

    public String fetchChallanDetails() {
        // Simulated data fetching (replace with actual database logic)

        String searchQuery = "SELECT SUM(c.creditHours) AS creditHours FROM StudentCourse sc JOIN Course c ON sc.courseID = c.courseID WHERE sc.userID = ? GROUP BY sc.userID";
        String creditHours = "";
        DatabaseConnector db = DatabaseConnector.getInstance();
        try (Connection connection = db.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditHours = resultSet.getString("creditHours");
                }
                else{
                    return "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int crdHrs = Integer.parseInt(creditHours);
        int amount = 10000 * crdHrs;
        return "Credit Hours: " + creditHours + "\n\nAmount: PKR" + String.valueOf(amount) + "\n\nPayable at: Faysal Bank";
    }

    public String fetchEligibleCourses(){   
        ArrayList<String> courseList = new ArrayList<>(fetchCoursesFromDatabase());
        String result = "";
        for (int i = 0; i < courseList.size(); i++) {
            String per = fetchAttendancePercentage(courseList.get(i));

            if ( per != null && !per.isEmpty() && Double.parseDouble(per) > 70){
                result = result + courseList.get(i) + "\n";
            }
        }
        return result;
    }

    public ArrayList<String> availableForRegistration(boolean select){

        String searchQuery = "SELECT courseName FROM Course WHERE courseID NOT IN ( SELECT courseID FROM StudentCourse  WHERE userID = ? ) AND semester = ? AND department = ? ";
        if (select){
            searchQuery = "SELECT courseName FROM Course WHERE courseID IN ( SELECT courseID FROM StudentCourse  WHERE userID = ? ) AND semester = ? AND department = ? ";
        }
        ArrayList<String> courseList = new ArrayList<>(5);
        DatabaseConnector db = DatabaseConnector.getInstance();

        try (Connection connection = db.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, semester);
            preparedStatement.setString(3, program);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String courseName = resultSet.getString("courseName");
                    courseList.add(courseName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }

    public boolean registerSelectedCourses(List<String> selectedCourses) {
        // Simulated backend logic (replace with actual database operations)
        String searchQuery = "SELECT courseId FROM Course Where courseName = ? AND department = ?";
        String insertQuery = "INSERT INTO StudentCourse (userId, courseId) VALUES (?, ?)";
        String courseId = "";
        DatabaseConnector db = DatabaseConnector.getInstance();

        try{
            for (String course : selectedCourses) {
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

                    pstmt.setString(1, course);
                    pstmt.setString(2, program);
                    
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            courseId = resultSet.getString("courseId");
                        }
                    }
                }
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

                    pstmt.setString(1, id);
                    pstmt.setString(2, courseId);

                    int rowsAffected = pstmt.executeUpdate();
    
                    if (rowsAffected > 0) {
                        System.out.println(course + " successfully!");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // Simulated success
    }

    public boolean withdrawSelectedCourses(List<String> selectedCourses) {
        // Simulated backend logic (replace with actual database operations)
        String searchQuery = "SELECT courseId FROM Course Where courseName = ? AND department = ?";
        String deleteQuery = "DELETE FROM StudentCourse WHERE UserId = ? AND courseID = ?";
        String courseId = "";
        DatabaseConnector db = DatabaseConnector.getInstance();

        try{
            for (String course : selectedCourses) {
                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

                    pstmt.setString(1, course);
                    pstmt.setString(2, program);
                    
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            courseId = resultSet.getString("courseId");
                        }
                    }
                }

                try(Connection conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

                    pstmt.setString(1, id);
                    pstmt.setString(2, courseId);

                    int rowsAffected = pstmt.executeUpdate();
    
                    if (rowsAffected > 0) {
                        System.out.println(course + " successfully!");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true; // Simulated success
    }
}
