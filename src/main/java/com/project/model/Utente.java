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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Column(name = "ID_U")
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "[a-zA-Z\\èàùìò]{1,50}", message="nome non ammesso")
	@Column(name = "Nome")
	private String nome;

	@Pattern(regexp = "[a-zA-Z\\èàùìò]{1,50}", message="cognome non ammesso")
	@Column(name = "Cognome")
	private String cognome;
	
	@Pattern(regexp = "[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,8}", message="mail non valida")
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(
		name = "utenti_corsi", joinColumns = @JoinColumn(name = "FK_UC", referencedColumnName = "ID_U" ),
		inverseJoinColumns = @JoinColumn(name="FK_CU", referencedColumnName = "ID_C")
	)
	private List<Corso> listaCorsi = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(
		name = "utente_ruolo", joinColumns = @JoinColumn(name = "FK_U", referencedColumnName = "ID_U" ),
		inverseJoinColumns = @JoinColumn(name="FK_R", referencedColumnName = "ID_G")
	)
	private List<Ruolo> listaRuoli = new ArrayList<>();

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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}

	public void setListaCorsi(List<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
	}

	public List<Ruolo> getListaRuoli() {
		return listaRuoli;
	}

	public void setListaRuoli(List<Ruolo> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}
	
	}
