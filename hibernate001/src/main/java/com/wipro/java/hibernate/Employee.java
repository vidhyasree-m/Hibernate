package com.wipro.java.hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "employee") // Table name in MySQL
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    public Employee() {}

    public Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public Employee(String empName) {
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
