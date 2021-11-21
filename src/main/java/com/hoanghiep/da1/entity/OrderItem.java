package com.hoanghiep.da1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq_gen")
	@SequenceGenerator(name = "order_item_seq_gen", sequenceName = "order_item_seq",
												initialValue = 200, allocationSize = 100)
	private int id;
	
	private int amount;
	
	private int quantity;
	
	@OneToOne
	private Product product;
}
