package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dto.CategoriaDto;
import com.project.service.CategoriaService;
import com.projectexception.ObjectNotFoundException;
import com.projectexception.UnauthorizedException;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorie() {
		
		try {
			List<CategoriaDto> listaCategorieDto = categoriaService.getCategorie();
			return Response.status(Response.Status.OK).entity(listaCategorieDto).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCategoria(@PathParam("id") int id) {
		
		try {

			categoriaService.deleteCategoria(id);
			return Response.status(Response.Status.OK).build();
			
		} catch (ObjectNotFoundException onfe) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
			catch (UnauthorizedException une) {
				return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	
	
}
