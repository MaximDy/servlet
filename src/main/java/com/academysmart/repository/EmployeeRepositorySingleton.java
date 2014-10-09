package com.academysmart.repository;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositorySingleton {
    private static volatile EmployeeRepositorySingleton instance = null;
    private List<Employee> emp = new ArrayList<>();

	public static EmployeeRepositorySingleton getRepository() {
		//TODO implement method that returns repository instance
        if (instance == null)
        {
            synchronized (EmployeeRepositorySingleton.class)
            {
                if (instance == null)
                {
                    instance = new EmployeeRepositorySingleton();
                    instance.addEmployee("fname1","lname2", "atata@gmail.com");
                    instance.addEmployee("fname2","lname2","atata2@gmail.com");
                }
            }
        }
		return instance;
	}

	public int addEmployee(String fname, String lname, String email)
//			throws IncorrectEmailException
    {
		//TODO implement method that adds an employee to repository
		//This method should check that email is not used by other employees
        Employee employee = new Employee(fname, lname, email);
//        for (Employee e : this.emp) {
//            if (e.getEmail().equals(employee.getEmail())) {
//                throw new IncorrectEmailException("This email is already used.");
//            }
//        }
        this.emp.add(employee);
        return this.emp.get(Employee.getCounter()-1).getId();
	}

	public List<Employee> getAllEmployees() {
		//TODO implement method that returns list of all employees
		return this.emp;
	}
}
