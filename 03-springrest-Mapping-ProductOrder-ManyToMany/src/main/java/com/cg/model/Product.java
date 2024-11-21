package com.cg.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="prod2")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="pro_id")
    @NotNull(message="Id cannot be null")
    private int id;

    @Column(name="pro_name")
    @NotNull(message="name cannot be null")
    private String pname;

    @Column(name="pro_price")
    @Min(50)
    private double price;
 // The 'products' property on 'Order'
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    public Product() {
        super();
    }

    public Product(@NotNull(message = "Id cannot be null") int id,
                   @NotNull(message = "name cannot be null") String pname, 
                   @Min(50) double price, Set<Order> orders) {
        super();
        this.id = id;
        this.pname = pname;
        this.price = price;
        this.order = order;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   

    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
    public String toString() {
        return "Product [id=" + id + ", pname=" + pname + ", price=" + price + ", order=" + order + "]";
    }
}
