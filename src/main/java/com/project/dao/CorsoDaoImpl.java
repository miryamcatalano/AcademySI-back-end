package com.project.dao;

import java.util.List;
import com.project.model.Categoria;
import com.project.model.Corso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CorsoDaoImpl implements Corso2Dao{

	/*@PersistenceContext
	 private EntityManager entityManager;
	 
	@Override
	public List<Corso> findByNomeAndIdCategoria(String nome, Categoria categoria) {
		String sql = "SELECT c FROM Corso c WHERE c.nome LIKE :nome AND c.categoria = :categoria";
		  return (List<Corso>) entityManager.createQuery(sql)
		    .setParameter("name", nome)
		    .setParameter("category", categoria).getResultList();
	}*/

}
