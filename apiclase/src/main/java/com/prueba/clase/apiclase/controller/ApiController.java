package com.prueba.clase.apiclase.controller;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.log.Log;
import com.prueba.clase.apiclase.dao.OrderPayDao;
import com.prueba.clase.apiclase.dao.UserDao;
import com.prueba.clase.apiclase.model.OrderPay;
import com.prueba.clase.apiclase.model.User;
import com.prueba.clase.apiclase.utils.ResponseMapping;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/api")
@EnableJpaRepositories("com.prueba.clase.apiclase.dao")
@EntityScan("com.prueba.clase.apiclase.model")
public class ApiController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderPayDao orderPayDao;
	
	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	public String login(HttpServletRequest r) {
		
		String email=r.getParameter("email");
		String clave=r.getParameter("clave");
		
		User user=null;
		user=userDao.findByEmailAndClave(email, clave);
		
		if(user==null) {
			return "Error de Login";
		}else {
			
			user.setToken(getRandomToken());
			userDao.save(user);
			return "Nuevo Token "+user.getToken();
		}
		
		
	}
	
	@RequestMapping(value="/addOrderPay" ,method=RequestMethod.POST)
	public String addOrderPay(HttpServletRequest r) {
		
		String name= r.getParameter("name");
		String descripcion= r.getParameter("descripcion");
		String address= r.getParameter("address");
		String realizado= r.getParameter("Realizado");
		User user= userDao.findByToken(r.getParameter("token"));
		
		OrderPay orderPay= new OrderPay();
		
		orderPay.setAddress(address);
		orderPay.setDescripcion(descripcion);
		orderPay.setName(name);
		orderPay.setRealizado(Boolean.parseBoolean(realizado));
		orderPay.setUser(user);
		
		orderPayDao.save(orderPay);
		
		return orderPay.getName();
		
	}
	
	@RequestMapping(value="/listUsers" ,method=RequestMethod.POST)
	public String showUsers(){
		
		Iterable<User> users= userDao.findAll();
		
		String usuarios="";
		for(User u: users) {
			usuarios= u.toString() + ",\n";
		}
		
		return usuarios;
		
	}
	
	public String getRandomToken() {
		
		int leftLimit = 97; // letter 'a'

	    int rightLimit = 122; // letter 'z'

	    int targetStringLength = 40;

	    Random random = new Random();

	    StringBuilder buffer = new StringBuilder(targetStringLength);

	    for (int i = 0; i < targetStringLength; i++) {

	        int randomLimitedInt = leftLimit + (int) 

	          (random.nextFloat() * (rightLimit - leftLimit + 1));

	        buffer.append((char) randomLimitedInt);

	    }

	    String generatedString = buffer.toString();
	    
	    return generatedString;
		
	}
	
	
	@RequestMapping(value="create_order",method=RequestMethod.POST)
	public ResponseEntity<ResponseMapping<OrderPay>> createOrder(HttpServletRequest r){
		
		String authheader = r.getHeader("Authorization");
		String name= r.getParameter("name");
		String description = r.getParameter("description");
		String address= r.getParameter("address");
		User user=null;
		
		user = userDao.findByToken(authheader);
		if(user!=null){
			OrderPay orderPay = new OrderPay();
			orderPay.setName(name);
			orderPay.setDescripcion(description);
			orderPay.setAddress(address);
			orderPay.setUser(user);
			orderPayDao.save(orderPay);
			ResponseMapping<OrderPay> rm= new ResponseMapping<>();
			
			rm.setError(false);
			rm.setMensaje("Funciona");
			rm.setObjeto(orderPay);
			
			
			return new ResponseEntity<ResponseMapping<OrderPay>>(rm,HttpStatus.OK);
		}
		return null;
	}
	
	
	@RequestMapping(value= "/getorder",method=RequestMethod.GET)
	public ResponseEntity<List<OrderPay>> getOrder(HttpServletRequest r){
		
		String authheader = r.getHeader("Authorization");
		User user=null;
		
		user= userDao.findByToken(authheader);
		
		if(user!=null) {
			return new ResponseEntity<List<OrderPay>>(user.getOrderPays(),HttpStatus.OK);
		}
		
		return null;
	}
	
	@RequestMapping(value="/updateOrder",method= RequestMethod.POST)
	public ResponseEntity<ResponseMapping> updateOrder(HttpServletRequest r){
		String authheader = r.getHeader("Authorization");
		
		System.out.print("\n\n\n\nValor");
		System.out.println(r.getParameter("id_order"));
		
		Long id_order = Long.parseLong(r.getParameter("id_order"));
		
		System.out.print("\n\n\n\nID ORDER");
		System.out.println(id_order);
		User user=null;
		user= userDao.findByToken(authheader);
		if(user!=null) {
			Optional <OrderPay> order_pay_optional= null;
			order_pay_optional = orderPayDao.findById(id_order);
			System.out.print("\n\n\n\nORDERPAY");
			System.out.println(order_pay_optional);
			if(order_pay_optional !=null) {
				OrderPay orderPay = order_pay_optional.get();
				orderPay.setRealizado(true);
				orderPayDao.save(orderPay);
				
				
				ResponseMapping rm= new ResponseMapping<>();
				
				rm.setError(false);
				rm.setMensaje("Orden actualizada correctamente");
				rm.setObjeto(orderPay);
				
				return new ResponseEntity<ResponseMapping>(rm,HttpStatus.OK);
			}
		}
		return null;
	}
	
	
	
}
