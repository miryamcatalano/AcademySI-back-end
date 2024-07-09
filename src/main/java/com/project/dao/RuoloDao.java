package com.project.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Ruolo;

public interface RuoloDao extends CrudRepository<Ruolo, Integer>{

	Optional<Ruolo> findById(int id);
}
