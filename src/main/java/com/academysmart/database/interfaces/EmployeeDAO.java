package com.academysmart.database.interfaces;

import com.academysmart.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    public void saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
}
