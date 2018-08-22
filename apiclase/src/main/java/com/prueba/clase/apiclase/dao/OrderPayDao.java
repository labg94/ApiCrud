package com.prueba.clase.apiclase.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.prueba.clase.apiclase.model.OrderPay;

@Transactional
public interface OrderPayDao extends CrudRepository<OrderPay, Long> {
	
	public Optional<OrderPay> findById(long id);
	

}
