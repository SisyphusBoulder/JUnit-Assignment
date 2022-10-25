package com.qa.junit.repository;

public class EmployeeAlreadyExistsException extends Exception {

	public EmployeeAlreadyExistsException(String msg) {
		super(msg);
	}

}
