package com.cg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Product;
import com.cg.service.IProductService;

import pom.cg.exception.ResourceNotFoundException;
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	IProductService productservice;
	@GetMapping("/productlist")
	public List<Product> findAllProducts(){
		return productservice.findAllProducts();
	}
	
	
	@PostMapping("/addproducts")
	public Product addProduct(@RequestBody Product product) {
		return productservice.addProduct(product);
	}
	@GetMapping("/findproduct/{id}")
	public Optional<Product> findProductById(@PathVariable int id) {
		return productservice.findProductById(id);
	}
	@PutMapping("/updateproduct/{id}")
	public Product updateProduct(@PathVariable int id,@RequestBody Product product) {
		return productservice.updateProduct(id, product);
	}
	@DeleteMapping("/deleteproduct/{id}")
	public void deleteProductById(@PathVariable int id) {
		productservice.deleteProductById(id);
	}
	

}
