package com.academysmart.model;

import com.academysmart.database.EmployeeDatabase;
import com.academysmart.exception.IncorrectEmailException;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
	//TODO implement model for employee
    private String fname;
    private String lname;
    private String email;
    private int id;

    public Employee(String namef, String namel, String email) throws IncorrectEmailException {
        this.fname = namef;
        this.lname = namel;
        this.email = email;
        if (this.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$"))
        {
            throw new IncorrectEmailException("wrong email format.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public String getEmail() {
        return this.email;
    }
}
