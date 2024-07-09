package com.project.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dto.UtenteAggiornamentoDto;
import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteRegistrazioneDto;
import com.project.model.Utente;
import com.project.service.UtenteService;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path ("/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registraUtente(@Valid @RequestBody UtenteRegistrazioneDto utenteRegDto) {
		
		try {
				if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utenteRegDto.getPassword())) {
					
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
				
				if(utenteService.existsUtente(utenteRegDto.getEmail())) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
					
				utenteService.registraUtente(utenteRegDto);
				return Response.status(Response.Status.OK).build();
				
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
	}
	
	
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUtenteByEmail(@PathParam("email") String email ) {
		
		try {
			Utente utente = utenteService.getUtenteByEmail(email);
			return Response.status(Response.Status.OK).entity(utente).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUtenti() {
		
		try {
			
			List<UtenteDto> listaUtenti = utenteService.getUtenti();
			return Response.status(Response.Status.OK).entity(listaUtenti).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		
		try {

			utenteService.deleteUtente(id);
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUtente(@RequestBody UtenteAggiornamentoDto utenteDto) {
		
		try {
			utenteService.updateUtente(utenteDto);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login (@RequestBody UtenteLoginRequestDto utenteLoginRequestDto) {
	  
	    try {
	    	
	    	if (utenteService.Login(utenteLoginRequestDto)) {
	        return Response.ok(utenteService.issueToken(utenteLoginRequestDto.getEmail())).build();
	      }
	
	    } catch (Exception e) {
	      return Response.status (Response.Status. BAD_REQUEST).build();
	    }
	
	    	return Response.status (Response.Status. BAD_REQUEST).build();
	  	}
	
	@PUT
	@Path("{id_Utente}/corso/{id_Corso}/subscribe")
	public Response addCorso(@PathParam("id_Utente") int idUtente, @PathParam ("id_Corso") int idCorso) {
		
		try {
			
			utenteService.addCorso(idUtente, idCorso);
			
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
		      return Response.status (Response.Status. BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("{id_Utente}/corso/{id_Corso}/unsubscribe")
	public Response removeCorso(@PathParam("id_Utente") int idUtente, @PathParam ("id_Corso") int idCorso) {
		
		try {
			
			utenteService.removeCorso(idUtente, idCorso);
			
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
		      return Response.status (Response.Status. BAD_REQUEST).build();
		}
	}
	
}