package com.spring.controller;

import java.util.List;
import java.util.Map;

import org.aspectj.org.eclipse.jdt.internal.core.CreateInitializerOperation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.entities.Product;
import com.spring.entities.User;

@Controller
public class LoginController {
	
	@Autowired
	 SessionFactory sf;
	
	@RequestMapping("/register")
	public  ModelAndView message()
	{
		System.out.println("I am here....:-)");
		ModelAndView mav=new ModelAndView();
		mav.addObject("user",new User());
		mav.setViewName("Register");
		
		return mav;
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
		System.out.println(uname);
		Session s=sf.openSession();
		Criteria cr = s.createCriteria(User.class);
		cr.add(Restrictions.eq("userName", uname));
		User user =(User) cr.uniqueResult();
		
		if (user.getUserPassword().equals(pass)) {
			System.out.println("user verified..");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("success");
			mav.setViewName("Login");
			RestTemplate rest=new RestTemplate();
			List<Product>list=rest.getForObject("http://localhost:9090/restws/rest/products",List.class);
			System.out.println(list);
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
