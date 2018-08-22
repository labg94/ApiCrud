package com.prueba.clase.apiclase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Order_pay")
public class OrderPay {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id")
		private long id;
	
		@Column(name="name")
		private String name;
		
		@Column(name="descripcion")
		private String descripcion;
		
		@Column(name="address")
		private String address;
		
		@Column(name="REALIZADO", nullable=false)
		private boolean realizado;
		
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="USER_ID",nullable=false)
		@JsonIgnore
		private User user;
		
		public OrderPay() {}

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
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @param address the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * @return the realizado
		 */
		public boolean isRealizado() {
			return realizado;
		}

		/**
		 * @param realizado the realizado to set
		 */
		public void setRealizado(boolean realizado) {
			this.realizado = realizado;
		}

		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}

		/**
		 * @param user the user to set
		 */
		public void setUser(User user) {
			this.user = user;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "OrderPay [id=" + id + ", name=" + name + ", descripcion=" + descripcion + ", address=" + address
					+ ", realizado=" + realizado + "]";
		}
		
		
		
}
