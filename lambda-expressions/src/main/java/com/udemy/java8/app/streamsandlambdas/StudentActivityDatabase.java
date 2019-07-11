package com.udemy.java8.app.streamsandlambdas;

import java.util.Arrays;
import java.util.List;

public class StudentActivityDatabase {

    /**
     * Total of 6 students in the database.
     * @return
     */
    public static List<Activity> getAllStudentActivities(){
    	
    	Activity activity1 = new Activity("Adam", "swimming");
    	Activity activity2 = new Activity("Jenny", "swimming");
    	Activity activity3 = new Activity("Dave", "swimming");
    	Activity activity4 = new Activity("Adam", "basketball");
    	Activity activity5 = new Activity("Dave", "basketball");
    	Activity activity6 = new Activity("Jenny", "basketball");
    	Activity activity7 = new Activity("Emily", "swimming");
    	Activity activity8 = new Activity("Emily", "basketball");
    	Activity activity9 = new Activity("Emily", "golf");
    	Activity activity10 = new Activity("Adam", "tennis");
    	Activity activity11 = new Activity("Adam", "baseball");
    	return Arrays.asList(activity1,activity2,activity3,
    			activity4,activity5,activity6,activity7,activity8,activity9,activity10,activity11);
    }
}
