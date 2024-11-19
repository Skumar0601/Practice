package com.example.demo.controller;
//http://localhost:9090/actuator/health
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.IProductService;
import com.example.demo.service.ProductService;
@CrossOrigin("http://localhost:4200") // Cors error
@RestController
@RequestMapping("/api") //http://loclahost:9090/api/products
//http://localhost:8080/swagger-ui/index.html
//   http://localhost:9090/hello
public class ProductController {

	@GetMapping("/hello")
		public String printHello()
		{
			return " Hi Spring";
		}
		
		@Autowired
		IProductService productService ;
	
	
	    //@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
	//@GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
		@GetMapping("/products")
	    List<Product> products() {
	        return productService.getProductsFromDatabase();
	    }
	    
	
		
		 //http://localhost:9090/products/101
		@GetMapping("/products/{id}")
		Optional<Product>findByProductIdFromDBWithException(@PathVariable int id) throws ResourceNotFoundException
		{	Optional<Product> product = productService.getProductById(id);
		
        	product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        	System.out.println(id);
        return product;	
		}
		
		
		//Demo of @RequestParam:  http://localhost:9090/products?id=1
				@GetMapping("/products/req")
				Product findByProductIdUsingrequestParam(@RequestParam int id) throws ResourceNotFoundException
				{	Product product = productService.getProductById(id)
		        		.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		           System.out.println(id);
		        return product;	
				}
				
		
				
	    @PostMapping("/products")
	    public Product createEmployee(@Valid @RequestBody Product newProduct) {
	        return productService.createProduct(newProduct);
	    }
	       
	    @PutMapping("/products/{id}")
	    public ResponseEntity<Product> updateEmployee(@PathVariable(value = "id")  Integer productId, @Valid @RequestBody Product newProduct) throws ResourceNotFoundException 
	    {
	        return productService.updateProduct(productId, newProduct);
	    }
	    

	    @DeleteMapping("/products/{id}")
	    public Map<String,Boolean> deleteProduct(@PathVariable (value="id") Integer productId) throws ResourceNotFoundException
	    {
	    	return productService.deleteProduct(productId);
	    }
	    // Find product by name
	    //http://localhost:9090/api/products/names/Chair
		@GetMapping("/products/name/{pname}")
		public ResponseEntity<Product> getProductByName(@PathVariable String pname) throws ResourceNotFoundException {
			Optional<Product> product = productService.getProductByName(pname);
			return product.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Product not found with name :: " + pname));
		}
	
		@GetMapping("/products/names/{pname}")
	    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String pname) throws ResourceNotFoundException {
	        List<Product> products = productService.getProductsByName(pname);
	        return ResponseEntity.ok(products);
	    }
		
		 @GetMapping("/products/count/{pname}")
		    public ResponseEntity<Long> countProductsByName(@PathVariable String pname) {
		        Long count = productService.countProductsByName(pname);
		        return ResponseEntity.ok(count);
		    }
}