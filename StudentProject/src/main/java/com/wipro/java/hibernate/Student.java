package com.wipro.java.hibernate;

public class Student {
	//instance variables
    private int rollNumber;
    private String name;
    //Default constructor
    public Student() {
    	
    }
    //parameterized constructor
    public Student(String name) {
    	this.name = name;
    }
    //getter and setter methods
    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Student{" +
               "rollNumber=" + rollNumber +
               ", name='" + name + '\'' +
               '}';
    }
}
