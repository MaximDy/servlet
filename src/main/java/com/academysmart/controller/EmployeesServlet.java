package com.academysmart.controller;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
public class EmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process GET requests
        request.setAttribute("employees", EmployeeRepositorySingleton.getRepository().getAllEmployees());
        getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process data that client sent to server with POST method.
		//It could include adding employee to repository,
		//validating email, redirecting client to a page where employee list is displayed etc.
//        String fname = request.getParameter("first_name");
//        String lname = request.getParameter("last_name");
//        String email = request.getParameter("email_in");
//        try {
//            EmployeeRepositorySingleton.getRepository().addEmployee(fname, lname, email);
//        } catch (IncorrectEmailException e) {
//            request.setAttribute("errMsg", e);
//        }
	}
}
