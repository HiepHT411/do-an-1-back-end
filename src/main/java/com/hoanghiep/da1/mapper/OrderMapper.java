package com.hoanghiep.da1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hoanghiep.da1.entity.Order;
import com.hoanghiep.da1.payload.OrderRequest;
import com.hoanghiep.da1.service.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {

	
	private final ModelMapper modelMapper;
	
	private final OrderService orderService;
	
	private Order convertToEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }
	
	public Order postOrder(OrderRequest orderRequest) {
		System.out.println("2222");
        return orderService.postOrder(convertToEntity(orderRequest), orderRequest.getProductsId());
    }
}
