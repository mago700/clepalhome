package com.clepalhome.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.clepalhome.vendas.domain.Categoria;
import com.clepalhome.vendas.dto.CategoriaDTO;
import com.clepalhome.vendas.repositories.CategoriaRepository;
import com.clepalhome.vendas.services.exception.DataIntegrityException;
import com.clepalhome.vendas.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired 
	private CategoriaRepository repo;
	
//===================================== LISTAR =============================================
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
//==================================== INSERIR =============================================
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
//==================================== ALTERAR =============================================
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
//==================================== DELETAR =============================================
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
			}
			catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
			}
	}
	
//==================================== FIND ALL =============================================
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
//==================================== FIND PAGE ============================================
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);	
	}
}
