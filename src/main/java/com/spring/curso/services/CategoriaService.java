package com.spring.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.spring.curso.domain.Categoria;
import com.spring.curso.repositories.CategoriaRepository;
import com.spring.curso.services.exceptions.DataIntegrityException;
import com.spring.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
			if(obj == null) {
				throw new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
			}
		return obj.orElse(null);	
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id){
		find(id);
		try {
		repo.deleteById(id);
		
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categora que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
		
	}
}
