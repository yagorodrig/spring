package com.spring.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.curso.domain.Cliente;
import com.spring.curso.repositories.ClienteRepository;
import com.spring.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
			if(obj == null) {
				throw new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
			}
		return obj.orElse(null);
	
		
	}

}
