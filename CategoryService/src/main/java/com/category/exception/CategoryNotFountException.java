package com.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryNotFountException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryNotFountException(String message) {
		super(message);
	}

}
 