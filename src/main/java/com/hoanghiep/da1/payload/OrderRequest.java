package com.hoanghiep.da1.payload;

import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderRequest {

	private Double totalPrice;
	
    private Map<String, Integer> productsId;

    @NotBlank(message = "Fill in the input field")
    private String username;

    @NotBlank(message = "Fill in the input field")
    private String address;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;
}
