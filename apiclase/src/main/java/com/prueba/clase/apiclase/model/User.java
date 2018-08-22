package com.prueba.clase.apiclase.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "TOKEN")
	private String token;
	
	@Column(name= "clave")
	private String clave;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="user")
	private List<OrderPay> orderPays;
	
	public User() {}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the orderPays
	 */
	public List<OrderPay> getOrderPays() {
		return orderPays;
	}

	/**
	 * @param orderPays the orderPays to set
	 */
	public void setOrderPays(List<OrderPay> orderPays) {
		this.orderPays = orderPays;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", token=" + token + ", clave=" + clave
				+ ", orderPays=" + orderPays + "]";
	}

	
	
}
