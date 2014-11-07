package com.academysmart.repository;

import com.academysmart.database.implementation.EmployeeDAOImp;
import com.academysmart.database.interfaces.EmployeeDAO;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;
import com.academysmart.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeRepositorySingleton {
    private static volatile EmployeeRepositorySingleton instance = null;
    private List<Employee> emp = new ArrayList<>();
    @Autowired
    @Qualifier("e_service")
    private EmployeeService employeeService;

    private EmployeeRepositorySingleton(){
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
        this.employeeService.saveEmployee(employee);
        return this.emp.get(this.emp.size()-1).getId();
    }

	public List<Employee> getAllEmployees(){
		//TODO implement method that returns list of all employees
        if (!this.emp.containsAll(this.employeeService.getAllEmployees()))
        {
            this.emp.clear();
            this.emp.addAll(this.employeeService.getAllEmployees());
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
