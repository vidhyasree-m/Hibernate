package com.wipro.java.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Load Hibernate configuration
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        // Start transaction
        session.beginTransaction();

        // Create an Employee
        Employee emp = new Employee("John Doe");
        session.persist(emp);

        // Commit transaction
        session.getTransaction().commit();

        // Retrieve employee
        Employee retrievedEmp = session.get(Employee.class, emp.getEmpId());
        System.out.println("Retrieved Employee: " + retrievedEmp.getEmpName());

        // Close session
        session.close();
        factory.close();
    }
}
