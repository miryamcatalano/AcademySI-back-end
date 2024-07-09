package com.project.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CorsoDao;
import com.project.dao.RuoloDao;
import com.project.dao.UtenteDao;
import com.project.dto.CorsoDto;
import com.project.dto.UtenteAggiornamentoDto;
import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteLoginResponseDto;
import com.project.dto.UtenteRegistrazioneDto;
import com.project.model.Corso;
import com.project.model.Ruolo;
import com.project.model.Utente;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;


@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteDao utenteDao;
	
	@Autowired
	private RuoloDao ruoloDao;
	
	@Autowired
	private CorsoDao corsoDao;

	@Override
	public void registraUtente(UtenteRegistrazioneDto utenteRegDto) {
		
		String sha256hex = DigestUtils.sha256Hex(utenteRegDto.getPassword());
		utenteRegDto.setPassword(sha256hex);
		
		ModelMapper modelMapper = new ModelMapper();
		Utente utente = modelMapper.map(utenteRegDto, Utente.class);
		utenteDao.save(utente);
	}

	@Override
	public List<UtenteDto> getUtenti() {		
			    
		    List<Utente> utenti = (List<Utente>) utenteDao.findAll();
		    
		    List<UtenteDto> utentiDto = new ArrayList<>();
		    
		    for (Utente utente : utenti) {
		      ModelMapper mapper = new ModelMapper();
		      UtenteDto utenteDto = mapper.map(utente, UtenteDto.class);
		      List<CorsoDto> corsiDto = new ArrayList<>();
		      utente.getListaCorsi().forEach(u -> corsiDto.add(mapper.map(u, CorsoDto.class)));
		      utenteDto.setCorsiDto(corsiDto);
		      utentiDto.add(utenteDto);
		    }
		   
		    return utentiDto;
		
	}

	@Override
	public Utente getUtenteByEmail(String email) {

		Utente utente = utenteDao.findUtenteByEmail(email);
		return utente;
	}

	@Override
	public void deleteUtente(int id) {
		Optional<Utente> userOptDb = utenteDao.findById(id);
		if(userOptDb.isPresent()) {
			utenteDao.delete(userOptDb.get());
		}
	}
	
	@Override
	public void addCorso(int idUtente, int idCorso) {
		
		Optional<Utente> utenteOpt = utenteDao.findById(idUtente);
		Optional<Corso> corsoOpt = corsoDao.findById(idCorso);
		
		if(utenteOpt.isPresent() && corsoOpt.isPresent()) {
			
			Utente utenteDb = (Utente) utenteOpt.get();
			
			Corso corsoDb = (Corso) corsoOpt.get();
			
			utenteDb.getListaCorsi().add(corsoDb);
			
			utenteDao.save(utenteDb);
		}
	}
	
	@Override
	@Transactional
	public void removeCorso(int idUtente, int idCorso) {
		
		Optional<Utente> utenteOpt = utenteDao.findById(idUtente);
		Optional<Corso> corsoOpt = corsoDao.findById(idCorso);
		
		if(utenteOpt.isPresent() && corsoOpt.isPresent()) {
			
			Utente utenteDb = (Utente) utenteOpt.get();
			
			Corso corsoDb = (Corso) corsoOpt.get();
			
			utenteDb.getListaCorsi().remove(corsoDb);
			
			utenteDao.save(utenteDb);
			
		}
	}
	

	@Override
	public boolean existsUtente(String email) {

		return utenteDao.existsByEmail(email);
	}

	@Override
	public void updateUtente(UtenteAggiornamentoDto utenteAggDto) {
	
		Utente utenteDb = utenteDao.findUtenteByEmail(utenteAggDto.getEmail());
			
			if(utenteDb != null) {
				utenteDb.setNome(utenteAggDto.getNome());
				utenteDb.setCognome(utenteAggDto.getCognome());
				utenteDb.setEmail(utenteAggDto.getEmail());
				
				List<Ruolo> ruoliUtente = new ArrayList<>();
				Optional<Ruolo> ruoloDb = ruoloDao.findById(utenteAggDto.getIdRuolo());
				
				if(ruoloDb.isPresent()) {
					Ruolo ruolo = ruoloDb.get();
					
					ruolo.setId(utenteAggDto.getIdRuolo());
					
					ruoliUtente.add(ruolo);
					utenteDb.setListaRuoli(ruoliUtente);
				}
				
				utenteDao.save(utenteDb);
			}
	}

	@Override
	public boolean Login(UtenteLoginRequestDto utenteLoginRequestDto) {

		Utente utente = new Utente();
		
		utente.setEmail(utenteLoginRequestDto.getEmail());
		utente.setPassword(utenteLoginRequestDto.getPassword());
		
		String sha256hex = DigestUtils.sha256Hex(utente.getPassword());
		
		Utente logUtente = utenteDao.findByEmailAndPassword(utente.getEmail(), sha256hex);
		
		return logUtente != null ? true : false;
	}
	
	public UtenteLoginResponseDto issueToken(String email) {
		
		byte[] secret = "CiaoCiao159753245Miryam8745214236521423".getBytes();
		
		Key key = Keys.hmacShaKeyFor(secret);
		
		Utente infoUtente = utenteDao.findUtenteByEmail(email);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("nome", infoUtente.getNome());
		map.put("cognome", infoUtente.getCognome());
		map.put("email", email);
		
		List<String> ruoli = new ArrayList<>();
		
		for (Ruolo ruolo : infoUtente.getListaRuoli()) {
			ruoli.add(ruolo.getTipologia().name());
		}
		
		map.put("ruoli", ruoli);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UtenteLoginResponseDto token = new UtenteLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}

}
