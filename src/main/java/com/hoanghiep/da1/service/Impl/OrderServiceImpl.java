package com.hoanghiep.da1.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoanghiep.da1.entity.Order;
import com.hoanghiep.da1.entity.OrderItem;
import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.repository.OrderItemRepository;
import com.hoanghiep.da1.repository.OrderRepository;
import com.hoanghiep.da1.repository.ProductRepository;
import com.hoanghiep.da1.service.OrderService;
import com.hoanghiep.da1.service.mail.MailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	@Autowired
	private final OrderRepository orderRepository;

	@Autowired
	private final OrderItemRepository orderItemRepository;
	
	@Autowired
	private final ProductRepository productRepository;
	
	private MailSender mailSender;
	
	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAllByOrderByIdAsc();
	}

	@Override
	public List<Order> findOrderByEmail(String email) {
		
		return orderRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public Order postOrder(Order validOrder, Map<String, Integer> productsId) {
		Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        //System.out.println("3333");
        if(productsId.entrySet().isEmpty()) System.out.println("productsId is null");
        for (Map.Entry<String, Integer> entry : productsId.entrySet()) {
        	if(entry == null) System.out.println("entry is null");
            Product product = productRepository.findById(Integer.parseInt(entry.getKey())).get();
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setAmount((product.getPrice() * entry.getValue()));
            orderItem.setQuantity(entry.getValue());
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
            //System.out.println("4444");
        }
        
        order.getOrderItems().addAll(orderItemList);
        order.setTotalPrice(validOrder.getTotalPrice());
        order.setUsername(validOrder.getUsername());
        order.setAddress(validOrder.getAddress());
        order.setEmail(validOrder.getEmail());
        order.setPhoneNumber(validOrder.getPhoneNumber());

		
        orderRepository.save(order);
        
        //System.out.println("5555");
        
//        String subject = "Order #" + order.getId();
//        String template = "order-template";
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("order", order);
//        try {
//			mailSender.sendMessageHtml(order.getEmail(), subject, template, attributes);
//			System.out.println("6666");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
        //System.out.println("7777");
        return order;
	}

	@Override
	@Transactional
	public List<Order> deleteOrder(int orderId) {
		
		Order order = orderRepository.findById(orderId).get();
        order.getOrderItems().forEach(orderItem -> orderItemRepository.deleteById(orderItem.getId()));
        orderRepository.delete(order);
        return orderRepository.findAllByOrderByIdAsc();
	}
}
