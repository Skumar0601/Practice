package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.model.Product;

public interface IProductService {
	List<Product> findAllProducts();
	Product addProduct(Product product);
	Optional<Product> findProductById(int id);
	Product updateProduct(int id, Product product); 
	void deleteProductById(int id);
	
	
	
}
