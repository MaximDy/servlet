package com.academysmart.database.implementation;

import com.academysmart.database.interfaces.EmployeeDAO;
import com.academysmart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "e_daoImp")
public class EmployeeDAOImp implements EmployeeDAO{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void saveEmployee(Employee employee){
        this.hibernateTemplate.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.hibernateTemplate.loadAll(Employee.class);
    }
}
