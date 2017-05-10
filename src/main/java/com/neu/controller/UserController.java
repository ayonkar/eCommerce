package com.neu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.validator.ProductValidator;
import com.neu.validator.UserValidator;
import com.neu.dao.ProductDAO;
import com.neu.dao.UserDAO;
import com.neu.exception.UserException;
import com.neu.pojo.Product;
import com.neu.pojo.User;

@Controller
//@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	@Qualifier("productValidator")
	ProductValidator p_validator;
	

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	 
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		//return "index";
		//System.out.print("inside welcome");
		return "user-home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected String loginUser() throws Exception {
	return "index";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	protected String userHome(HttpServletRequest request) throws Exception {
	System.out.println("Logout");
	request.getSession().invalidate();
	return "user-home";
	}
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	protected String userHome() throws Exception {
	System.out.println("Same page");
	return "user-home";
	}
	
	/*@RequestMapping(value = "/createProducts", method = RequestMethod.GET)
	protected String createProduct() throws Exception {
		
		return "create-product";
	}*/
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u.getUsername().equalsIgnoreCase("admin") && u.getPassword().equalsIgnoreCase("admin"))
			{
				System.out.println("Admin login panel");
				session.setAttribute("user", u);
				return "user-admin";
			}
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			//System.out.println("UserName" + u.getUsername());
			//System.out.println("UserName1" + request.getParameter("username"));
			
			/*if(!request.getParameter("username").equals(userDao.getUserByName(request.getParameter("username"))))
			{
				return "invalidUserError";
				
			}*/
			
			
			
			session.setAttribute("user", u);
			
			return "user-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("user-home", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/productList1", method = RequestMethod.GET)
	protected ModelAndView displayElectronicsProduct() throws Exception {
		
		System.out.println("Electronics product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getProductByCategoryName("electronics"));
		mv.setViewName("electronics");
		return mv;

	}
	
	@RequestMapping(value = "/productList2", method = RequestMethod.GET)
	protected ModelAndView displayBooksProduct() throws Exception {
		
		System.out.println("Books product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getProductByCategoryName("books"));
		mv.setViewName("books");
		return mv;

	}
	
	@RequestMapping(value = "/productList3", method = RequestMethod.GET)
	protected ModelAndView displayApparelsProduct() throws Exception {
		
		System.out.println("Apparels product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getProductByCategoryName("apparels"));
		mv.setViewName("apparels");
		return mv;

	}
	
	@RequestMapping(value = "/product/addElectronicsToCart", method = RequestMethod.POST)
	protected ModelAndView addElectronics(HttpServletRequest request) throws Exception {
		
		System.out.println("View Electronics");
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		//mv.addObject("productList", productDao.getAll());
		if(u == null)
		{
			mv.setViewName("user-home");
			return mv;
		}
		else
		{
		//mv.addObject("productList", productDao.getAll());
		mv.addObject("productList", productDao.getProductByCategoryName("electronics"));
		mv.setViewName("addElectronics");
		return mv;
		}
		
		

	}
	
	@RequestMapping(value = "/product/addBooksToCart", method = RequestMethod.POST)
	protected ModelAndView addBooks(HttpServletRequest request) throws Exception {
		
		System.out.println("View Books");
		ModelAndView mv = new ModelAndView();
		//mv.addObject("productList", productDao.getAll());
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		//mv.addObject("productList", productDao.getAll());
		if(u == null)
		{
			mv.setViewName("user-home");
			return mv;
		}
		else
		{
		mv.addObject("productList", productDao.getProductByCategoryName("books"));
		mv.setViewName("addBooks");
		return mv;
		}

	}
	
	@RequestMapping(value = "/product/addApparelsToCart", method = RequestMethod.POST)
	protected ModelAndView addApparels(HttpServletRequest request) throws Exception {
		
		System.out.println("View Electronics");
		ModelAndView mv = new ModelAndView();
		//mv.addObject("productList", productDao.getAll());
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		//mv.addObject("productList", productDao.getAll());
		if(u == null)
		{
			mv.setViewName("user-home");
			return mv;
		}
		else
		{
		mv.addObject("productList", productDao.getProductByCategoryName("apparels"));
		mv.setViewName("addApparels");
		return mv;
		}

	}
	
}
