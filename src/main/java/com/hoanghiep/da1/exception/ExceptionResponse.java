package com.hoanghiep.da1.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private Date date;
	private String message;
	private String detail;
	
	@Override
	public String toString() {
		return "ExceptionResponse [date=" + date + ", message=" + message + ", detail=" + detail + "]";
	}
	
	
}
