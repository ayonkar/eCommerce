package com.neu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.neu.validator.ProductValidator;

import java.util.Date;
import java.util.List;
import com.neu.dao.UserDAO;
import com.neu.dao.CategoryDAO;
import com.neu.dao.ProductDAO;
import com.neu.pojo.Cart;
import com.neu.pojo.Category;
import com.neu.pojo.Order;
import com.neu.pojo.Product;
import com.neu.pojo.User;
import com.neu.pojo.User;


@Controller
public class ProductController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	/*@Autowired
	@Qualifier("userValidator")
	UserValidator validator;*/
	
	@Autowired
	@Qualifier("productValidator")
	ProductValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/createProducts", method = RequestMethod.GET)
	protected String createProduct() throws Exception {
		
		return "create-product";
	}
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.POST)
	protected ModelAndView editProduct() throws Exception {
		
		System.out.println("Edit product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getAll());
		mv.setViewName("edit-product");
		return mv;

	}
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET)
	protected ModelAndView editPreviousProduct() throws Exception {
		
		System.out.println("Edit previous product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getAll());
		mv.setViewName("edit-product");
		return mv;

	}
	
	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	protected ModelAndView updateProductpage(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
		System.out.println("Update product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getAll());
		//System.out.println("Checking before the update. Product selected is "+product.getProductId());
		
		Product p=productDao.getProductByName(product.getProductName());
		productDao.update(p);
		mv.addObject("productId", p.getProductId());
		mv.setViewName("update-product");
		return mv;

	}
	
	/*@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	protected ModelAndView deleteProductpage(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
		System.out.println("Delete product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getAll());
		System.out.println("Checking before the update. Product selected is "+product.getProductId());
		
		Product p=productDao.getProductByName(product.getProductName());
		productDao.delete(p);
		//mv.addObject("productId", p.getProductId());
		mv.setViewName("edit-product");
		return mv;

	}*/
	

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	protected ModelAndView updateProduct(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		
		System.out.println("Update product");
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productDao.getAll());
		System.out.println("Checking before the update. Product selected is "+product.getProductId());
		Product p=productDao.getProductByID(product.getProductId());
		System.out.println("Product ID after fetching is "+p.getProductId());
		p.setPrice(product.getPrice());
		p.setProductName(product.getProductName());
		productDao.update(p);
		mv.setViewName("added-products");
		return mv;

	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	protected ModelAndView registerNewProduct(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {

		validator.validate(product, result);

		if (result.hasErrors()) {
			return new ModelAndView("create-product", "product", product);
		}
		
			ModelAndView mv = new ModelAndView();
			String categoryName= request.getParameter("dropdown");
			System.out.println("Value from dropdown "+categoryName);
			
			Category category=categoryDao.getCategoryByName(categoryName);
			//product.se(categoryName);
			System.out.println("Value retrieved from DAO "+category.getCategoryName());
			product.setCategory(category);
		    
			System.out.print("registerNewProduct");

			//User u = userDao.register(user);
			Product p = productDao.add(product);
			
			request.getSession().setAttribute("product", p);
			mv.addObject("productList", productDao.getAll());
			mv.addObject("product", p);
			mv.setViewName("added-products");
//			return new ModelAndView("added-products", "product", p);
			return mv; 
		}
	
	@RequestMapping(value = "/product/buyElectronics", method = RequestMethod.POST)
	protected ModelAndView addElectronicsToCart(HttpServletRequest request) throws Exception {
			
		    ModelAndView mv = new ModelAndView();
			
			HttpSession session = (HttpSession) request.getSession();
			System.out.println("Buy Electronics");
			User u = (User) session.getAttribute("user");
			
			List<Cart> list = productDao.getAllProductsForCart(u.getPersonID());
			String name = request.getParameter("productName");
			
			for(Cart c : list)
			{
				if(name.equals(c.getProductName()))
				{
					mv.setViewName("error");
					return mv;
				}
				
			}
			
			
			Product p = productDao.getProductByName(name);
			long userId = u.getPersonID();
			
			productDao.addToCart(p, userId);
			
			mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
			mv.setViewName("user-cart");
			return mv;

	}
	
	@RequestMapping(value = "/product/buyBooks", method = RequestMethod.POST)
	protected ModelAndView addBooksToCart(HttpServletRequest request) throws Exception {
			
		    ModelAndView mv = new ModelAndView();
			
			HttpSession session = (HttpSession) request.getSession();
			System.out.println("Buy Books");
			User u = (User) session.getAttribute("user");
			
			List<Cart> list = productDao.getAllProductsForCart(u.getPersonID());
			String name = request.getParameter("productName");
			
			for(Cart c : list)
			{
				if(name.equals(c.getProductName()))
				{
					mv.setViewName("error");
					return mv;
				}
				
			}
			
			
			Product p = productDao.getProductByName(name);
			long userId = u.getPersonID();
			
			productDao.addToCart(p, userId);
			
			mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
			mv.setViewName("user-cart");
			return mv;

	}
	
	@RequestMapping(value = "/product/buyApparels", method = RequestMethod.POST)
	protected ModelAndView addApparelsToCart(HttpServletRequest request) throws Exception {
			
		    ModelAndView mv = new ModelAndView();
			
			HttpSession session = (HttpSession) request.getSession();
			System.out.println("Buy Electronics");
			User u = (User) session.getAttribute("user");
			
			List<Cart> list = productDao.getAllProductsForCart(u.getPersonID());
			String name = request.getParameter("productName");
			
			for(Cart c : list)
			{
				if(name.equals(c.getProductName()))
				{
					mv.setViewName("error");
					return mv;
				}
				
			}
			
			
			Product p = productDao.getProductByName(name);
			long userId = u.getPersonID();
			
			productDao.addToCart(p, userId);
			
			mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
			mv.setViewName("user-cart");
			return mv;

	}
	
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	protected ModelAndView userCart(HttpServletRequest request) throws Exception {
		
		System.out.println("View Cart");
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		//mv.addObject("productList", productDao.getAll());
		if(u == null)
		{
			mv.setViewName("user-home");
			return mv;
		}
		mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
		mv.setViewName("user-cart");
		return mv;

	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	protected ModelAndView userOrders(HttpServletRequest request) throws Exception {
		
		System.out.println("View Order");
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		//mv.addObject("productList", productDao.getAll());
		//mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
		if(u == null)
		{
			mv.setViewName("user-home");
			return mv;
		}
		
		mv.addObject("order", productDao.getAllFromOrder(u.getPersonID()));
		mv.setViewName("order-placed");
		return mv;

	}
	
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
	protected ModelAndView removeFromCart(HttpServletRequest request, @RequestParam String name) throws Exception {
		
		System.out.println("Remove products from cart");
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		
		System.out.println("RequestProduct" + name);
		Cart c = productDao.findFromCart(u.getPersonID(),name);
		
		System.out.println("the found cart id is : "+c.getCartId() );
		//mv.addObject("productList", productDao.getAll());
		productDao.removeFromCart(c);
		mv.addObject("product", productDao.getAllProductsForCart(u.getPersonID()));
		mv.setViewName("user-cart");
		return mv;

	}
	
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	protected ModelAndView placeOrder(HttpServletRequest request) throws Exception {
		
		System.out.println("Check out");
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		
		List<Cart> list = productDao.getAllProductsForCart(u.getPersonID());
		for(Cart c : list)
		{
			Product product = productDao.getProductByName(c.getProductName());
			Order o = new Order();
			o.setProduct(product);
			o.setShipStatus("Shipped");
			o.setOrderDate(new Date());
			o.setUser(u);
			productDao.saveOrder(o);
			productDao.removeFromCart(c);
		}
		
		//mv.addObject("productList", productDao.getAll());
		mv.addObject("order", productDao.getAllFromOrder(u.getPersonID()));
		mv.setViewName("order-placed");
		return mv;

	}
	
	
	
	
	@RequestMapping(value = "/authorize/error", method = RequestMethod.GET)
	protected ModelAndView authoizeError(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("authorizeError");
		return mv;

	}
	
}
