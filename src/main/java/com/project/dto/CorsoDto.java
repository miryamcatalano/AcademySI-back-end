package com.project.dto;

public class CorsoDto {
	
private int id;
	
	private String nome;
	
	private String descrizione_breve;
	
	private String descrizione_completa;
	
	private int durata;
	
	private String categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione_breve() {
		return descrizione_breve;
	}

	public void setDescrizione_breve(String descrizione_breve) {
		this.descrizione_breve = descrizione_breve;
	}

	public String getDescrizione_completa() {
		return descrizione_completa;
	}

	public void setDescrizione_completa(String descrizione_completa) {
		this.descrizione_completa = descrizione_completa;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	

}
