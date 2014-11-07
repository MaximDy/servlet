package com.academysmart.model;

import com.academysmart.exception.IncorrectEmailException;
import javax.persistence.*;

@Entity
@Table(name = "Employee", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))
public class Employee {
	//TODO implement model for employee
    private String fname;
    private String lname;
    @Column(name = "email", unique = true)
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_emp")
    @SequenceGenerator(name = "id_emp", sequenceName = "id_emp_seq")
    private int id;

    public Employee(String namef, String namel, String email) throws IncorrectEmailException {
        this.fname = namef;
        this.lname = namel;
        this.email = email;
//        if (this.email.matches("/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
//        {
//            throw new IncorrectEmailException("wrong email format.");
//        }
    }

    public Employee() {}

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
