package com.santosjuliano.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santosjuliano.cursomc.domain.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1, "Informática");
		
		Categoria c2 = new Categoria(2, "Escritório");
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(c1);
		categorias.add(c2);
		
		return categorias;
	}
}
