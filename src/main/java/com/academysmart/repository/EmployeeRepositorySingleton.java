package com.academysmart.repository;

import com.academysmart.database.implementation.EmployeeDAOImp;
import com.academysmart.database.interfaces.EmployeeDAO;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeRepositorySingleton {
    private static volatile EmployeeRepositorySingleton instance = null;
    private List<Employee> emp = new ArrayList<>();
    @Autowired
    @Qualifier("e_daoImp")
    private EmployeeDAO employeeDAOImp;

    private EmployeeRepositorySingleton(){
    }

    public void setEmployeeDAOImp(EmployeeDAOImp employeeDAOImp) {
        this.employeeDAOImp = employeeDAOImp;
    }

    public static EmployeeRepositorySingleton getRepository() {
		//TODO implement method that returns repository instance
        if (instance == null)
        {
            synchronized (EmployeeRepositorySingleton.class)
            {
                if (instance == null)
                {
                    instance = new EmployeeRepositorySingleton();
                }
            }
        }
		return instance;
	}

	public int addEmployee(String fname, String lname, String email)
            throws ServletException
    {
		//TODO implement method that adds an employee to repository
		//This method should check that email is not used by other employees
        Employee employee = new Employee(fname, lname, email);
        return this.addEmployee(employee);
	}

    public int addEmployee(Employee employee) throws ServletException {
        if (employee.getFname().equals("") || employee.getLname().equals("") || employee.getEmail().equals(""))
        {
            throw new ServletException("One of the fields wasn't field up.");
        }
        for (Employee e : this.emp) {
            if (e.getEmail().equals(employee.getEmail())) {
                throw new IncorrectEmailException("This email is already used.");
            }
        }
        this.emp.add(employee);
        this.employeeDAOImp.saveEmployee(employee);
        return this.emp.get(this.emp.size()-1).getId();
    }

	public List<Employee> getAllEmployees(){
		//TODO implement method that returns list of all employees
        if (!this.emp.containsAll(this.employeeDAOImp.getAllEmployees()))
        {
            this.emp.clear();
            this.emp.addAll(this.employeeDAOImp.getAllEmployees());
            this.emp.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return (o1.getId() - o2.getId());
                }
            });
            return this.emp;
        }
        else
        {
            return this.emp;
        }
	}
}
