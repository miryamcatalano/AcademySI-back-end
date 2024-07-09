package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CategoriaDao;
import com.project.dao.CorsoDao;
import com.project.dto.CategoriaDto;
import com.project.dto.CorsoAggiornamentoDto;
import com.project.dto.CorsoDto;
import com.project.dto.UtenteDto;
import com.project.model.Categoria;
import com.project.model.Corso;
import com.project.model.Utente;


@Service
public class CorsoServiceImpl implements CorsoService {
	
	@Autowired
	private CorsoDao corsoDao;
	
	@Autowired
	private CategoriaDao categoriaDao;

	@Override
	public List<CorsoDto> getCorsi() {
		
		List<Corso> corsi = (List<Corso>) corsoDao.findAll();
	    
	    List<CorsoDto> corsiDto = new ArrayList<>();
	    
	    for (Corso corso : corsi) {
	      ModelMapper mapper = new ModelMapper();
	      CorsoDto corsoDto = mapper.map(corso, CorsoDto.class);
	      	      
	      corsiDto.add(corsoDto);
	    }
		return corsiDto;
	}

	@Override
	public Corso createCorso(CorsoDto corsoDto) {

		ModelMapper modelMapper = new ModelMapper();

		Corso corso = modelMapper.map(corsoDto, Corso.class);
		return corsoDao.save(corso);
	}

	@Override
	public void updateCorso(CorsoAggiornamentoDto corso) {

		Optional<Corso> corsoDbOpt = corsoDao.findById(corso.getId());
		
		Corso corsoDb = corsoDbOpt.get();
		
		if(corsoDb != null) {
			corsoDb.setNome(corso.getNome());
			corsoDb.setDescrizione_breve(corso.getDescrizione_breve());
			corsoDb.setDescrizione_completa(corso.getDescrizione_completa());
			corsoDb.setDurata(corso.getDurata());
			//corsoDb.setCategoria(corso.getCategoria());
			
			corsoDao.save(corsoDb);
		}
	}

	@Override
	public void deleteCorso(int id) {
		
		Optional<Corso> corsoOptDb = corsoDao.findById(id);

		if(corsoOptDb.isPresent()) {
			corsoDao.delete(corsoOptDb.get());
		}		
	}

	@Override
	public Corso getCorsoById(CorsoDto corsoDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Corso> findCorsoByCategoria(int idCategoria){
		
		Optional<Categoria> categoria = categoriaDao.findById(idCategoria);
		
		if(categoria.isPresent()) {
			List<Corso> listaCorsi = corsoDao.findByCategoria(categoria.get());
			return listaCorsi;
		}
		
		else {
			return new ArrayList<>();
		}
		
	}

	@Override
	public void deleteCorsoByCategoria(int idCategoria) {

		List<Corso> listaCorsi = findCorsoByCategoria(idCategoria);
		
		for(Corso corso : listaCorsi) {
			corsoDao.delete(corso);
		}
	}

	

}
