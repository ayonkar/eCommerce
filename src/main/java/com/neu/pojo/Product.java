package com.neu.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javassist.bytecode.ByteArray;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.neu.pojo.Category;
import com.neu.pojo.User;
import java.io.Serializable;



@Entity
@Table(name="product_table")
public class Product implements Serializable{
	
	@Id
	@Column(name="ProductId")
	@GeneratedValue
	private long productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name ="price")
	private String price;
	

	@Column(name="productImage")
	private byte[] productImage;
	
	//@Transient
	//private CommonsMultipartFile photo;
	
	public Product(){
		
	}
	
	
	@ManyToOne
	private Category category;

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public byte[] getProductImage() {
		return productImage;
	}


	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	/*public CommonsMultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}*/
	


	/*public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}*/

	/*public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}*/


	}
	

