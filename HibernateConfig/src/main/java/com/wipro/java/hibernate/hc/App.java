package com.wipro.java.hibernate.hc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;



public class App 
{
    public static void main( String[] args )
    {
        Student stu = new Student(101,"vidhya",18,"female","AP","2233445566");
        
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(stu);
        tx.commit();
        session.close();
        System.out.println("Record Saved Successfully");
    }
}
