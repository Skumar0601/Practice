package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.model.Order;

public interface IOrderService {
	List<Order> getALLOrders();
	Order createOrder(Order order);
	Optional<Order> findOrderById(int id);
	void deleteOrderById(int id);

}
