package com.example.StudentService.exceptions;

public class NoStudentFoundException extends RuntimeException {
	public NoStudentFoundException() {
		super("NoStudentFoundException");
	}

}
