package com.wipro.java.hibernate.usecase2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "roll_number")
	    private int rollNumber;

	    @Column(name = "name")
	    private String name;
	    
	    @Column(name = "branch")
	    private String branch;
	    
	    @Column(name = "gender")
	    private String gender;
	    
	    @Column(name = "age")
	    private int age;
	     
	    //Default constructor
	    public Student() {
	    	
	    }

	    //parameterized constructor
	    public Student(String name, String branch, String gender, int age) {
	        this.name = name;
	        this.branch = branch;
	        this.gender = gender;
	        this.age = age;
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

		public String getBranch() {
			return branch;
		}

		public void setBranch(String branch) {
			this.branch = branch;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	    
}
