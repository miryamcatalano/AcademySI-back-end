package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dto.CorsoAggiornamentoDto;
import com.project.dto.CorsoDto;
import com.project.jwt.JWTTokenNeeded;
import com.project.jwt.Secured;
import com.project.model.Corso;
import com.project.service.CorsoService;

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
/*
@Secured(role="Admin")
@JWTTokenNeeded*/
@Path("/corsi")
public class CorsoController {
	
	@Autowired
	private CorsoService corsoService;
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCorsi() {
		
		try {
			
			List<CorsoDto> listaCorsi = corsoService.getCorsi();
			return Response.status(Response.Status.OK).entity(listaCorsi).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
		
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCorso(@PathParam("id") int id) {
		
		try {

			corsoService.deleteCorso(id);
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCorso(@Valid @RequestBody CorsoDto corsoDto) {
		
		try {
				
				corsoService.createCorso(corsoDto);
				return Response.status(Response.Status.OK).build();
				
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCorso(@RequestBody CorsoAggiornamentoDto corsoAggDto) {
		
		try {
			corsoService.updateCorso(corsoAggDto);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	   @DELETE
	    @Path("/categoria/{id}")
	    public void deleteCorsoByCategory(@PathParam("id") int idCategoria) {
	        corsoService.deleteCorsoByCategoria(idCategoria);
	    }
	   
	   

}
