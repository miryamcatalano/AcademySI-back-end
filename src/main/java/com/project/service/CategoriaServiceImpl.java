package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CategoriaDao;
import com.project.dto.CategoriaDto;
import com.project.model.Categoria;
import com.projectexception.ObjectNotFoundException;
import com.projectexception.UnauthorizedException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	public List<CategoriaDto> getCategorie() {

		List<Categoria> listaCategorie = (List<Categoria>) categoriaDao.findAll();
		List<CategoriaDto> listaCategorieDto = new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		for(Categoria categoria : listaCategorie ) {
			CategoriaDto categoriaDto = new CategoriaDto();
			modelMapper.map(categoria, categoriaDto);
			listaCategorieDto.add(categoriaDto);
		}
		
		return listaCategorieDto;
	}
	
	//fai firma metodo
	public void deleteCategoria(int id) throws UnauthorizedException, ObjectNotFoundException {
		Optional<Categoria> categoriaOpt = categoriaDao.findById(id);
		if(!categoriaOpt.isEmpty()) {
			
			Categoria categoria = categoriaOpt.get();
			if(!categoria.getListaCorsi().isEmpty()) {
				categoriaDao.delete(categoria);
			}
			else {
				throw new UnauthorizedException();
			}
			
		}
		
		else {
			throw new ObjectNotFoundException();
		}
		
		
	}

}
