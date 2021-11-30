package com.hoanghiep.da1.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "feedback")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_at")
	private String date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	
	
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", description=" + description + ", date=" + date + ", user=" + user + "]";
	}



	public Feedback(String description) {
		super();
		this.description = description;
	}
	
	
}
