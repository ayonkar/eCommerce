/*
package com.neu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.dao.CategoryDAO;
import com.neu.dao.ProductDAO;

public class CustomerController {

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	/*@Autowired
	@Qualifier("userValidator")
	UserValidator validator;*/
	
	/*@Autowired
	@Qualifier("productValidator")
	ProductValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}*/
	
	
	/*@RequestMapping(value = "/productList1", method = RequestMethod.GET)
	protected String displayProduct() throws Exception {
		
		return "success";
	}*/
//}
