package com.academysmart.exception;

public class IncorrectEmailException extends ServletException {
    public IncorrectEmailException()
    {
        super();
    }

    public IncorrectEmailException(String s)
    {
        super(s);
    }
}