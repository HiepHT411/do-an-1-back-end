package com.hoanghiep.da1.configuration;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable
{
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, 
    					HttpServletResponse response,
    					AuthenticationException authException) throws IOException,ServletException 
    {
    logger.error("Unauthorized error: {}", authException.getMessage());
    System.out.println("JwtAuthenticationEntryPointCall");
	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
//The purpose of this file is to handle exceptions and 
//whenever JWT  token is not validated it throws Unauthorised exception.