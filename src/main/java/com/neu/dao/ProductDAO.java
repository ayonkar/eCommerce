package com.neu.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import org.hibernate.HibernateException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.neu.exception.UserException;
import com.neu.pojo.Email;
import com.neu.pojo.OrderItem;
import com.neu.pojo.User;
import com.neu.pojo.Cart;
import com.neu.pojo.Category;
import com.neu.pojo.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import com.neu.pojo.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;


public class ProductDAO extends DAO{

	
	/*public List<Product> getAll(long categoryId){

		Query query = getSession().createQuery("FROM Category as cat WHERE cat.id = " + categoryId);
		Category category = (Category)query.uniqueResult();
		return new ArrayList<Product>(category.getProduct());
	}*/
	
	/*public List<OrderItem> getAllProduct(long userId){

		Query query = getSession().createQuery("FROM User as user WHERE user.id = " + userId);
		User user = (User)query.uniqueResult();
		return user.getOrderItem();
	}*/


	public Product get(long itemId){

		Product product = (Product)getSession().get(Product.class, itemId);
		return product;
	}

	
	public List<Product>getAll(){

		Query query = getSession().createQuery("FROM Product");
		return query.list();
	}
	
	public List<Cart>getAllProductsForCart(Long userId){

		Query query = getSession().createQuery("FROM Cart where userId=:userId");
		query.setLong("userId", userId);
		return query.list(); 
	}
	
	
	
	public List<Product> getProductByCategoryName(String categoryName){

		Query query = getSession().createQuery("FROM Product where category.categoryName=:categoryName");
		query.setString("categoryName", categoryName);
		return query.list();
	}
	
	public Cart findFromCart(Long userId, String productName){
		
		System.out.println("PersonId" + userId + "ProductName" + productName);
		Query query = getSession().createQuery("from Cart where userId=:userId AND productName=:productName");
		query.setLong("userId", userId);
		query.setString("productName", productName);
		Cart c = (Cart)query.uniqueResult();
		return c;		
	}
	
	public void removeFromCart(Cart c){
		begin();
		getSession().delete(c);
		commit();
		
	}
	
	public void saveOrder(Order order){
		begin();
		getSession().save(order);
		commit();
	}
	
	public List<Order> getAllFromOrder(Long userId){

		Query query = getSession().createQuery("FROM Order where user=:user");
		query.setLong("user", userId);
		return query.list();

	}
	
	
	
	public Product add(Product product) throws IOException{
		
		try {
			begin();
			System.out.println("inside ProductDAO");
			
			Product product_instance = new Product();
			//List<Product> product_instance = new ArrayList<Product>();
			
			/*product_instance.setCategory(product_instance.getCategory());
			//product_instance.setProductImage(file.getBytes());
	        product_instance.setProductId(product_instance.getProductId());
	        product_instance.setProductName(product_instance.getProductName());
	        product_instance.setPrice(product_instance.getPrice());*/

			
			getSession().save(product);
			commit();
			return product;

		} catch (HibernateException e) {
			rollback();
			throw new IOException("Exception while creating user: " + e.getMessage());
		}
	}
	

	public void saveItem(Product product){

		getSession().saveOrUpdate(product);
	}
	
	// To add item to user // 
	public void addItemToUser(long userId,long productId){

		Product product_inst = (Product)getSession().get(Product.class,productId);
		
		User user_inst = (User)getSession().get(User.class,userId);
		//user_inst.getProduct().add(product_inst);
		
		getSession().save(user_inst);
	}
	
	public void removeAllItems(long userId){

		User user_ins = (User) getSession().get(User.class,userId);
		//user_ins.getProduct().clear();
	}
	
	public void delete(long productId){
		
		Product product_inst = (Product)getSession().get(Product.class,productId);
		getSession().delete(product_inst);
	}
	
public Product getProductByName(String productName){
		
		begin();
        Query query = getSession().createQuery("from Product where productName = :productName");
        query.setString("productName", productName);
        Product p = (Product) query.uniqueResult();
        commit();
        return p;
	}


public Product getProductByID(long productId){
	
	begin();
    Query query = getSession().createQuery("from Product where productId = :productId");
    query.setLong("productId", productId);
    Product p = (Product) query.uniqueResult();
    commit();
    return p;
}


	public void update(Product product) throws IOException{
		
	
		begin();
		getSession().update(product);
		commit();
		
	}
	
	public void delete(Product product) throws IOException{
		
		
		begin();
		getSession().delete(product);
		commit();
		
	}
	
	public void addToCart(Product product, long userId) throws IOException{
		
		try {
			begin();
			System.out.println("inside ProductDAO for cart");
			
			Cart cart = new Cart();
			
			cart.setProductId(product.getProductId());
			cart.setProductName(product.getProductName());
			cart.setPrice(product.getPrice());
			cart.setUserId(userId);
	        			
			getSession().save(cart);
			commit();

		} catch (HibernateException e) {
			rollback();
			throw new IOException("Exception while creating user: " + e.getMessage());
		}
	}
	
}
