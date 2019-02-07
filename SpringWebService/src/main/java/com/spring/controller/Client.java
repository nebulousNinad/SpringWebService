package com.spring.controller;

import static org.hamcrest.CoreMatchers.is;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.spring.entities.Product;

public class Client {

	public static void main(String[] args) {


		
	// Get 
		
		RestTemplate rt = new RestTemplate();
		
		
		  // Product p =  rt.getForObject("http://localhost:8080/SpringWebService/restone", Product.class);
		
		
		 //  ResponseEntity<Product> p =  rt.getForEntity("http://localhost:8080/SpringWebService/restone",  Product.class);
		
		HttpEntity<Product> entity =  new HttpEntity<Product>(new Product( "HeadPhones", 250,"electronics"));
		
	//	entity.getHeaders().getAccept().get(0)
		
		//ResponseEntity<Product> p  = rt.exchange("http://localhost:8080/facebookWS/fb/products/",HttpMethod.POST,entity,Product.class);
			
//		System.out.println(p);

	}

}
