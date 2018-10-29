package com.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entities.Product;

@RestController
public class RestControllerClass {


@RequestMapping(value ="/rest1" ,produces=MediaType.APPLICATION_JSON_VALUE)
	
	public Product getProd1()
	{
		System.out.println("I am getting produc 1)");
		//List<Product> prodlist = new ArrayList<Product>();
		Product p = new Product(1, "maggi",50 , "food");		
		return p;
	}
	
}
