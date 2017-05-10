package com.neu.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.neu.pojo.Email;
import com.neu.pojo.Product;

import java.io.Serializable;



@Entity
@Table(name = "user_table")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person implements Serializable {
	
	
	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Email email;
	
	
	
	//@Column(name = "userRole")
	//private String userrole;
	

	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<OrderItem> orderItem = new ArrayList<OrderItem>();
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	/*public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}*/


	
	/*public String getUserrole()
	{
		return userrole;
	}
	
	public void setUserrole(String userrole){
		
		this.userrole = userrole;
	}*/
	

}
