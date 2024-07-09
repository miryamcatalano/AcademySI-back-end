package com.project.service;

import java.util.List;

import com.project.dto.CategoriaDto;
import com.projectexception.ObjectNotFoundException;
import com.projectexception.UnauthorizedException;

public interface CategoriaService {

	List<CategoriaDto> getCategorie();
	
	public void deleteCategoria(int id) throws UnauthorizedException, ObjectNotFoundException;
}
