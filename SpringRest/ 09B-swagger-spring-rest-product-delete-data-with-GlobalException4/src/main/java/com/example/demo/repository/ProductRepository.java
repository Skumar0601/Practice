package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	 Optional<Product> findByPname(String pname);
	 //  @Query("SELECT p FROM Product p WHERE p.pname = :pname")
	 List<Product> findAllByPname(String pname); 	
	 
	 @Query("SELECT COUNT(p) FROM Product p WHERE p.pname = :pname")
	    Long countByProductName(String pname);

}
