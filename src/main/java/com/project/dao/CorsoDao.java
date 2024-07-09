package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Categoria;
import com.project.model.Corso;

public interface CorsoDao extends CrudRepository<Corso, Integer>, Corso2Dao{
 
	public List<Corso> findByCategoria(Categoria categoria);
}
