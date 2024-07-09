package com.project.service;

import java.util.List;

import com.project.dto.UtenteAggiornamentoDto;
import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteLoginResponseDto;
import com.project.dto.UtenteRegistrazioneDto;
import com.project.model.Utente;

public interface UtenteService {
	
	void registraUtente(UtenteRegistrazioneDto utenteRegDto);
	
	List<UtenteDto> getUtenti();
	
	Utente getUtenteByEmail(String email);
		
	void deleteUtente(int id);
	
	void updateUtente(UtenteAggiornamentoDto utenteAggDto);
	
	boolean existsUtente(String email);
	
	public void addCorso(int idUtente, int idCorso);
	
	public void removeCorso(int idUtente, int idCorso);
	
	boolean Login(UtenteLoginRequestDto utenteLoginRequestDto);
	
	public UtenteLoginResponseDto issueToken(String email);


}
