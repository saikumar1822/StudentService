package com.example.StudentService.exceptions;

public class CourseNotFoundException extends RuntimeException {
	public CourseNotFoundException() {
		super("CourseNotFoundException");
	}

}
