package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int studentId;
    private String studentName;
    private String course;
    @Value("3")
    private int year;
    public Student(@Value("1001") int studentId, @Value("Akshaya") String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }
    @Value("FSAD")
    public void setCourse(String course) {
        this.course = course;
    }
    public void setYear(int year) {
    	this.year=year;
    }
    public void display() {
        System.out.println("Student Details");
        System.out.println("ID     : " + studentId);
        System.out.println("Name   : " + studentName);
        System.out.println("Course : " + course);
        System.out.println("Year   : " + year);
    }
}
