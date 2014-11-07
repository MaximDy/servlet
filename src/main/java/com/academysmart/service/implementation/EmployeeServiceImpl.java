package com.academysmart.service.implementation;

import com.academysmart.database.interfaces.EmployeeDAO;
import com.academysmart.model.Employee;
import com.academysmart.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "e_service")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    @Qualifier("e_daoImp")
    EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        this.employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }
}
