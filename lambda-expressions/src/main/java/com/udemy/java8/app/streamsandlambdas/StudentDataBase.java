package com.udemy.java8.app.streamsandlambdas;

import java.util.Arrays;
import java.util.List;

public class StudentDataBase {

    /**
     * Total of 6 students in the database.
     * @return
     */
    public static List<Student> getAllStudents(){

        /**
         * 2nd grade students
         */
        Student student1 = new Student("Adam",2,3.6, "male","");
        Student student2 = new Student("Jenny",2,3.8,"female", "");
        Student student3 = new Student("Dave",2,3.6, "male","");
        Student student4 = new Student("Emily",2,3.6, "male","");

        List<Student> students = Arrays.asList(student1,student2,student3,student4);
        return students;
    }
}
