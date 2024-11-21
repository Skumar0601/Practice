package com.cg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.model.Order;
import com.cg.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	@Autowired
	OrderService orderservice;
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderservice.getALLOrders();
	}
	
	@PostMapping("/order")
	public Order createOrder(@RequestBody Order order) {
		return orderservice.createOrder(order);
		
	}
	@GetMapping("/orders/{id}")
	public Optional<Order> findOrderById(@PathVariable int id){
		return orderservice.findOrderById(id);
	}
	
	@DeleteMapping("/orderdelete/{id}")
	public void deleteOrderById(@PathVariable int id) {
		orderservice.deleteOrderById(id);
	}

}
