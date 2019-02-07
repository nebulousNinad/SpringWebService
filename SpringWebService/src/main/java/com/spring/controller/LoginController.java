package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.org.eclipse.jdt.internal.core.CreateInitializerOperation;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.entities.Product;
import com.spring.entities.ProductList;
import com.spring.entities.User;




@Controller
public class LoginController {
	
	@Autowired
	 SessionFactory sf;
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping("/register")
	public  ModelAndView message()
	{
		logger.debug("register is executed!");
		logger.warn("WArning Dont enter..");
		
		System.out.println("I am here....:-)");
		ModelAndView mav=new ModelAndView();
		mav.addObject("user",new User());
		mav.setViewName("Register");	
		return mav;
	}
	
	
	@RequestMapping(value ="/rest" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public @ResponseBody Product getProd()
	{
		System.out.println("I am getting product....:-)");
		//List<Product> prodlist = new ArrayList<Product>();
		Product p = new Product(1, "maggi",50 , "food");
		
		
		return p;
	}
	
	// creating conflict
	
	
@RequestMapping(value ="/restone" , produces = {"text/xml","application/json"}  ) //{MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}  ,  produces = "application/xml" 
	
	public @ResponseBody Product getProd1()
	{
		System.out.println("I am getting produc 1)");
		//List<Product> prodlist = new ArrayList<Product>();
		Product p = new Product(1, "maggi",50 , "food");		
		return p;
	}
	
	
	
	@RequestMapping("/registeruser")
	public  ModelAndView registerUser(@ModelAttribute("user") User user)
	{
		System.out.println("Inside login user ");
		System.out.println("name :"+user.getUserName());
		ModelAndView mav=new ModelAndView();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		session.save(user);
		session.flush();
		tr.commit();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/login")
	public  String loginPage()
	{
		
		return "Login";
	}
	
	
	@RequestMapping("/loginuser")
	public  ModelAndView loginUser(@RequestParam Map<String,String>reqpar )
	{
		String uname=reqpar.get("userName");
		String pass=reqpar.get("userPassword");
		System.out.println("userName "+uname);
		System.out.println("userPassword"+pass);
		
		
		
		Session s=sf.openSession();
		Criteria cr = s.createCriteria(User.class);
		cr.add(Restrictions.eq("userName", uname));
		User user =(User) cr.uniqueResult();
		
		if (user.getUserPassword().equals(pass)) {
			System.out.println("user verified..");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("success");
			mav.setViewName("Login");
			RestTemplate restTemplate=new RestTemplate();
			
			
			///Product p=restTemplate.getForObject("http://localhost:8080/restws/rest/products",Product.class);
			//System.out.println(p);
			String str =restTemplate.getForObject("http://localhost:8080/restws/rest/products",String.class);
			System.out.println(str);
			
			Product p = restTemplate.getForObject("http://localhost:8080/SpringWebService/rest", Product.class);
			System.out.println(p);
			//exchange code		
			
			return mav;
			
		}
		else {
			System.out.println("wrong password redirecting to login ");
			ModelAndView mav = new ModelAndView();
			
			return mav;
		}
		
	}
	
	public List<Product>getAllProducts()
	{
		RestTemplate rest=new RestTemplate();
		List<Product>list=rest.getForObject("http://localhost:8090/restws/rest/products",List.class);
		System.out.println(list);
		return list;
		
	}
	

}
