package com.wipro.java.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertStudent {

	public static void main(String[] args) {

		// Load Hibernate Configuration
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		// Create SessionFactory
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		insertStudents(session);
		fetchStudentById(session, 2);
		updateStudentById(session, 3, "Sandhya");
		deleteStudentById(session, 4);

		// Close SessionFactory
		session.close();
		factory.close();

   }

	// Inserting Multiple Students
	public static void insertStudents(Session session) {
		List<String> names = Arrays.asList("Divya", "Harika", "Siri", "Nikitha", "Kavitha");
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			for (String name : names) {
				Student student = new Student(name);
				session.persist(student);
			}
			tx.commit();
			System.out.println("Students inserted successfully!");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	// Fetching Student by ID
	public static void fetchStudentById(Session session, int id) {
		try {
			Student student = session.get(Student.class, id);
			if (student != null) {
				System.out.println("Student found: " + student);
			} else {
				System.out.println("Student with ID " + id + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Updating Student Name by ID
	public static void updateStudentById(Session session, int id, String newName) {
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Student student = session.get(Student.class, id);
			if (student != null) {
				student.setName(newName);
				session.merge(student);
				tx.commit();
				System.out.println("Updated Student: " + student);
			} else {
				System.out.println("Student with ID " + id + " not found.");
			}
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	// Deleting Student by ID
	public static void deleteStudentById(Session session, int id) {
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Student student = session.get(Student.class, id);
			if (student != null) {
				session.remove(student);
				tx.commit();
				System.out.println("Deleted Student with ID " + id);
			} else {
				System.out.println("Student with ID " + id + " not found.");
			}
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
