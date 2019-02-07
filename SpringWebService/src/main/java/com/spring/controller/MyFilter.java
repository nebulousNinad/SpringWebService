package com.spring.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

@WebFilter("/*")
public class MyFilter implements Filter{

	private static final Logger logger = Logger.getLogger(MyFilter.class);
	
	static {
		System.out.println(" Filter loaded...");
	     }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println(" Filter Initialized.....");
		
	}
     
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(" req received");
		logger.warn(" Just now request has been received..... be aware........... "  );
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
}
