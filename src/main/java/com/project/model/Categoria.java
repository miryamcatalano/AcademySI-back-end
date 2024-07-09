package com.project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID_CA")
	private int id;
	
	@Column(name = "Nome_categoria")
	private String Categoria;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REFRESH , fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Corso> listaCorsi = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	
	
}
