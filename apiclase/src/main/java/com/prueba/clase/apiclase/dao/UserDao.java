package com.prueba.clase.apiclase.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.prueba.clase.apiclase.model.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

	public User findByEmailAndClave(String email,String clave);
	public User findByToken(String token);
	
	@Override
	@Transactional
	public Iterable<User> findAll();
	
	
}
