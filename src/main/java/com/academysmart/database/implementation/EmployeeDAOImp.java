package com.academysmart.database.implementation;

import com.academysmart.database.interfaces.EmployeeDAO;
import com.academysmart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "e_daoImp")
public class EmployeeDAOImp implements EmployeeDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveEmployee(Employee employee){
        this.jdbcTemplate.update
                ("insert into EMPLOYEE (id, f_name, l_name, email) values (null, (?), (?), (?))",
                        employee.getFname(), employee.getLname(), employee.getEmail());
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList("select * from EMPLOYEE");
        for (Map row : rows) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
            employee.setFname((String) row.get("F_NAME"));
            employee.setLname((String) row.get("L_NAME"));
            employee.setEmail((String) row.get("EMAIL"));
            employees.add(employee);
        }
        return employees;
    }

}
