package com.wipro.java.hibernate.usecase2;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
    	//loading Hibernate and creating session
        Configuration config = new Configuration().configure().addAnnotatedClass(Student.class);
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        //adding students list
        try {
        	List<Student> students = new ArrayList<>();
        	students.add(new Student("Vidhya", "CSE", "Female", 20));
        	students.add(new Student("Divya", "ECE", "Female", 22));
        	students.add(new Student("Kavitha", "CSE", "Female", 21));
        	students.add(new Student("Siri", "CHEM", "Female", 23));
        	students.add(new Student("Tharun", "MECH", "Male", 22));

            
            for (Student student : students) {
                session.persist(student);
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        
	    // To Read Data based on ID
        try {
            int studentId = 2; // student ID
            Student fetchedStudent = session.get(Student.class, studentId);
            if (fetchedStudent != null) {
                System.out.println("Student ID: " + fetchedStudent.getRollNumber());
                System.out.println("Student Name: " + fetchedStudent.getName());
                System.out.println("Student Branch: " + fetchedStudent.getBranch());
                System.out.println("Student Gender: " + fetchedStudent.getGender());
                System.out.println("Student Age: " + fetchedStudent.getAge());
            } else {
                System.out.println("Student not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //To Delete Data based on ID
        try  {
            int deleteId = 4; // student ID
            Student studentToDelete = session.get(Student.class, deleteId);
            if (studentToDelete != null) {
                session.remove(studentToDelete);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found for deletion!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //To Update Data based on ID & Name
        try {
            int updateId = 3; // student ID
            String updateName = "Divya";

            session = factory.openSession(); // Reopen session
            tx = session.beginTransaction(); // Start new transaction

            Student student = session.get(Student.class, updateId);
            if (student != null && student.getName().equals(updateName)) {
                student.setAge(25); // age update
                student.setBranch("AI"); // branch field update
                
                session.merge(student); // Merge was replaced with update
                tx.commit(); // Commit changes
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found for update!");
                tx.rollback(); // Rollback in case of failure
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

        session.close();
        factory.close();
    }
}

