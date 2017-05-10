package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Product;

public class ProductValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Product.class);
	}

	public void validate(Object obj, Errors errors) {
		Product product = (Product) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "error.invalid.product", "Product Id Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.invalid.product", "Product Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.product", "Price Required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
		//		"Email Required");
		
		// check if user exists
		
	}
	
}
