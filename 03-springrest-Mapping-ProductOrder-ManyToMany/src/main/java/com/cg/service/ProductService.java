package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Product;
import com.cg.repository.ProductRepository;
@Service
public class ProductService implements IProductService {
	@Autowired
	ProductRepository productrepo;

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return productrepo.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productrepo.save(product);
	}

	@Override
	public Optional<Product> findProductById(int id) {
		// TODO Auto-generated method stub
		return productrepo.findById(id);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		// TODO Auto-generated method stub
		return productrepo.save(product);
	}

	@Override
	public void deleteProductById(int id) {
		// TODO Auto-generated method stub
		productrepo.deleteById(id);
		
	}
	
	
	
}
	