package com.project.service;

import java.util.List;

import com.project.dto.CorsoAggiornamentoDto;
import com.project.dto.CorsoDto;
import com.project.model.Corso;

public interface CorsoService {
	
	List<CorsoDto> getCorsi();
	
	Corso createCorso(CorsoDto corso);
	
	void updateCorso(CorsoAggiornamentoDto corso);
	
	void deleteCorso(int id);
	
	Corso getCorsoById(CorsoDto corsoDto);
	
	public List<Corso> findCorsoByCategoria(int idCategoria);
	
	public void deleteCorsoByCategoria(int idCategoria);
	
}
