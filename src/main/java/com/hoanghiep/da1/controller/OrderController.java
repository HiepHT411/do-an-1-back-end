package com.hoanghiep.da1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoanghiep.da1.entity.Order;
import com.hoanghiep.da1.entity.User;
import com.hoanghiep.da1.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/v1/admin")
@Slf4j
public class OrderController {

	@Autowired
	private final OrderService orderService;
	
	@GetMapping("/orders/all")
    public ResponseEntity<List<Order>> getAllOrders() {
		log.info("Retrieving all orders");
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PostMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrdersByEmail(@RequestBody User user) {
    	log.info("get user's order by email-"+user.getEmail());
        return ResponseEntity.ok(orderService.findOrderByEmail(user.getEmail()));
    }

    @DeleteMapping("/orders/delete/{orderId}")
    public ResponseEntity<List<Order>> deleteOrder(@PathVariable(value = "orderId") int orderId) {
    	log.info("Deleting order by id-"+orderId);
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
