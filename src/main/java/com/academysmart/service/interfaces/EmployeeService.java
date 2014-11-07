package com.academysmart.service.interfaces;

import com.academysmart.model.Employee;

import java.util.List;

public interface EmployeeService {
    public void saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
}
