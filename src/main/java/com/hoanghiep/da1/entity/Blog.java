package com.hoanghiep.da1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	@NotBlank(message = "title may not be blank")
	private String title;
	
	@Column(name = "author")
	@NotBlank(message = "author may not be blank")
	private String author;
	
	@Column(name = "date")
	@NotBlank(message = "date may not be blank")
	private String date;
	
	@Column(name = "content")
	@NotBlank(message = "content may not be blank")
	private String content;
	
	
}
