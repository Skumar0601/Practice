package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	/*List<Product>  findAllBypname(String name);
	@Query("Select count(*) from  Product p where pname=:pname")
	 public int getCountByProduct(String pname);*/
	
			

}
  