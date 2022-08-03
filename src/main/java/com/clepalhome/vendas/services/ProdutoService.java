package com.clepalhome.vendas.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.clepalhome.vendas.domain.Categoria;
import com.clepalhome.vendas.domain.Produto;
import com.clepalhome.vendas.dto.ProdutoCategoriaDTO;
import com.clepalhome.vendas.dto.ProdutoDTO;
import com.clepalhome.vendas.repositories.CategoriaRepository;
import com.clepalhome.vendas.repositories.ProdutoRepository;
import com.clepalhome.vendas.services.exception.EmptyDataAccessException;
import com.clepalhome.vendas.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired 
	private ProdutoRepository repo;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private CategoriaService categoriaService;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
//==================================== INSERIR =============================================
	@Transactional
	public Produto insert(Produto obj) {
			obj.setId(null);
			obj = repo.save(obj);
			categoriaRepository.save(obj.getCategorias());
			return obj;
		}
	
	public Produto fromDto(ProdutoCategoriaDTO objDto) {
		Categoria cat = categoriaService.find( objDto.getCategoria());
		Produto obj = new Produto(null, objDto.getNome(), objDto.getDescricao(), objDto.getPreco(), cat);
		return obj;
	}
	
//==================================== ALTERAR =============================================
	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
		
	private void updateData(Produto newObj, Produto obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
		newObj.setPreco(obj.getPreco());	
	}
		
	public Produto fromDto(ProdutoDTO objDto) {
		return new Produto(objDto.getId(), objDto.getNome(), objDto.getDescricao(), objDto.getPreco(), null);
	}
	
//==================================== DELETAR =============================================
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
			}
			catch (EmptyResultDataAccessException e) {
				throw new EmptyDataAccessException("O produto não existe na base de dados.");
			}
	}
	
//==================================== ALTERAR =============================================
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
		

	}

}
