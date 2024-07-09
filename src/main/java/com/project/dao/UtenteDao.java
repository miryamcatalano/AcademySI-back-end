package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer>{

	Utente findByEmailAndPassword(String email, String sha256hex);
	
	Utente findUtenteByEmail(String email);
	
	boolean existsByEmail(String email);
}
