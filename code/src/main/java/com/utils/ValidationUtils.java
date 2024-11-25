package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationUtils {
     // Checks if the given string is an integer
     public static boolean isInteger(String input) {
          if (input == null || input.isEmpty()) {
               return false;
          }
          try {
               Integer.parseInt(input);
               return true;
          } catch (NumberFormatException e) {
               return false;
          }
     }

     public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            //Date parsedDate = dateFormat.parse(date);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
