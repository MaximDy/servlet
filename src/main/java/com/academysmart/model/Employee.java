package com.academysmart.model;

import com.academysmart.exception.IncorrectEmailException;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
	//TODO implement model for employee
    private String fname;
    private String lname;
    private String email;
    private static AtomicInteger counter = new AtomicInteger(1);
    private int id;

    public Employee(String namef, String namel, String email) throws IncorrectEmailException {
        this.fname = namef;
        this.lname = namel;
        this.email = email;
        if (this.email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\\\.[a-zA-Z0-9-.]+$"))
        {
            throw new IncorrectEmailException("wrong email format.");
        }
        this.id = counter.get();
    }

    public int getId() {
        return id;
    }

    public static void incCounter() {
        counter.getAndIncrement();
    }

    public static int getCounter() {
        return counter.get();
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
