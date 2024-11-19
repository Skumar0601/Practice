package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "Product")
public class Product {
	
	private int id;  
	@NotNull
	private String pname;   
	private double price;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String pname, double price) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "pname", nullable = false)
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@Column(name = "price", nullable = false)
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}  
}
