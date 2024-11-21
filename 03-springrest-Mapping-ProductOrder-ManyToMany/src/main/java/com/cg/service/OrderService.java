package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Order;
import com.cg.repository.OrderRepository;
@Service
public class OrderService implements IOrderService {
	@Autowired
	OrderRepository orderrepo;

	@Override
	public List<Order> getALLOrders() {
		// TODO Auto-generated method stub
		return orderrepo.findAll();
	}

	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return orderrepo.save(order);
	}

	@Override
	public Optional<Order> findOrderById(int id) {
		// TODO Auto-generated method stub
		return orderrepo.findById(id);
	}

	@Override
	public void deleteOrderById(int id) {
		// TODO Auto-generated method stub
		orderrepo.deleteById(id);
		
	}

}
