package com.users;

import javafx.collections.ObservableList;

public interface UserInterface {
     
     abstract boolean validateDpartment(String department);

     abstract void initialize();
     
     abstract ObservableList<String> fetchCoursesFromDatabase();
}