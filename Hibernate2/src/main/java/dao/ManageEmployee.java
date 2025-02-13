package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import entities.Employee;

public class ManageEmployee {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object: " + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageEmployee ME = new ManageEmployee();

		// Add employees
		Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
		Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
		Integer empID3 = ME.addEmployee("John", "Paul", 10000);

		// List employees
		ME.listEmployees();

		// Update employee salary
		ME.updateEmployee(empID1, 7000);

		// Delete an employee
		ME.deleteEmployee(empID2);

		// List employees after update & delete
		ME.listEmployees();
	}

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Integer employeeID = null;

		try {
			Employee employee = new Employee(fname, lname, salary);
			session.persist(employee); // Persist without returning ID
			tx.commit();
			employeeID = employee.getId(); // Retrieve the assigned ID
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			List<Employee> employees = session.createQuery("FROM Employee", Employee.class).getResultList();
			for (Employee employee : employees) {
				System.out.println("ID: " + employee.getId() + " | First Name: " + employee.getFirstName()
						+ " | Last Name: " + employee.getLastName() + " | Salary: " + employee.getSalary());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, int salary) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			Employee employee = session.find(Employee.class, EmployeeID);
			if (employee != null) {
				employee.setSalary(salary);
				session.merge(employee); // Use merge instead of update
			} else {
				System.out.println("Employee not found with ID: " + EmployeeID);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			Employee employee = session.find(Employee.class, EmployeeID);
			if (employee != null) {
				session.remove(employee); // Use remove instead of delete
			} else {
				System.out.println("Employee not found with ID: " + EmployeeID);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
