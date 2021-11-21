package com.hoanghiep.da1.service;

import java.util.List;
import java.util.Map;

import com.hoanghiep.da1.entity.Order;

public interface OrderService {

	List<Order> findAllOrders();

    List<Order> findOrderByEmail(String email);

    Order postOrder(Order validOrder, Map<Integer, Integer> productsId);

    List<Order> deleteOrder(int orderId);
	
	
}
