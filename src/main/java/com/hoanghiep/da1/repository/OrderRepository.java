package com.hoanghiep.da1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoanghiep.da1.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByOrderByIdAsc();

	List<Order> findByEmail(String email);
	
}
