package com.academysmart.controller;

import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;
import com.academysmart.repository.EmployeeRepositorySingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class EmployeesServlet {
    @Autowired
    private EmployeeRepositorySingleton ers;
    @RequestMapping(method = RequestMethod.GET)
    public String printEmployees (Employee employee, BindingResult result, ModelMap model){
        model.addAttribute("employees", ers.getAllEmployees());
        return "employees";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getEmployees (Employee employee, BindingResult result, ModelMap modelMap) {
        if (!result.hasErrors()) {
            try {
//                EmployeeRepositorySingleton.getRepository().addEmployee(employee);
                ers.addEmployee(employee);
            } catch (ServletException e) {
                modelMap.addAttribute("errMsg", e);
            }
        }
        return this.printEmployees(employee, result, modelMap);
    }
}
