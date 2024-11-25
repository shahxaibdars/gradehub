package com.academic;

public class Assessment {
     
     public static String validateMarks(int marks, String assessmentType){
          if (marks < 0){
               return "Mrks must not be Negative";
          } else if (assessmentType.equals("QUIZ") && marks > 20){
               return "Marks must not be grater than 20 in QUIZ";
          } else if (assessmentType.equals("ASSIGNMENT") && marks > 30){
               return "Marks must not be greater than 30 in ASSIGNMENT";
          } else if (assessmentType.equals("MIDS") && marks > 50){
               return "Marks must not be greater than 50 in MIDS";
          } else if (assessmentType.equals("FINAL") && marks > 100){
               return "Marks must not be greater than 100 in FINALS";
          }
          
          return "success";
     }

}
