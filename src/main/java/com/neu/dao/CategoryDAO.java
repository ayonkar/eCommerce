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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;


public class CategoryDAO extends DAO{
	
	public Category getCategoryByName(String categoryName){
		
		begin();
        Query query = getSession().createQuery("from Category where categoryName = :categoryName");
        query.setString("categoryName", categoryName);
        Category c = (Category) query.uniqueResult();
        commit();
        return c;
	}
}