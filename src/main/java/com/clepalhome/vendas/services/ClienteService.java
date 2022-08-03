package com.clepalhome.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clepalhome.vendas.domain.Cliente;
import com.clepalhome.vendas.domain.Morada;
import com.clepalhome.vendas.dto.ClienteDTO;
import com.clepalhome.vendas.dto.ClienteNewDTO;
import com.clepalhome.vendas.repositories.ClienteRepository;
import com.clepalhome.vendas.repositories.MoradaRepository;
import com.clepalhome.vendas.services.exception.DataIntegrityException;
import com.clepalhome.vendas.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository repo;
	
	@Autowired
	private MoradaRepository moradaRepository;
	
//===================================== LISTAR =============================================
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
//==================================== INSERIR =============================================	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		moradaRepository.saveAll(obj.getMorada());
		return obj;
	}
	
//==================================== INSERIR =============================================
		public Cliente fromDto(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail());
			
			Morada end = new Morada(null, objDto.getPais(), objDto.getMorada(), objDto.getComlementoMorada(), 
					objDto.getCodigoPostal(), objDto.getLocalidade(), cli);
			
			cli.getMorada().add(end);
			
			cli.getTelefones().add(objDto.getTelefone1());
			
			if (objDto.getTelefone2() != null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if (objDto.getTelefone3() != null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			return cli;
		}
	
//==================================== ALTERAR =============================================
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail());
	}
	
//==================================== DELETAR =============================================
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
		}
	}
	
//==================================== FIND ALL =============================================
	public List<Cliente> findAll() {
		return repo.findAll();
	}

//==================================== FIND PAGE ============================================
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	
	
}
