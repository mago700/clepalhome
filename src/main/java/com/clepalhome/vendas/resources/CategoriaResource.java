package com.clepalhome.vendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clepalhome.vendas.domain.Categoria;
import com.clepalhome.vendas.dto.CategoriaDTO;
import com.clepalhome.vendas.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin("*")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
		
//===================================== LISTAR =============================================
		@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
		public ResponseEntity<Categoria> find(@PathVariable Integer id){
			Categoria obj = service.find(id);
			return ResponseEntity.ok().body(obj);
	}

//==================================== INSERIR =============================================
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
			Categoria obj = service.fromDto(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
//==================================== ALTERAR =============================================
		@RequestMapping(value="/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
			Categoria obj = service.fromDto(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
//==================================== DELETAR =============================================
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)// verbos GET, POST, DELETE
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	
//==================================== FIND ALL =============================================
		@RequestMapping(method=RequestMethod.GET)// verbos GET, POST, DELETE
		public ResponseEntity<List<CategoriaDTO>> findAll(){
			List<Categoria> list = service.findAll();
			List<CategoriaDTO> listDto =  list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}

//==================================== FIND PAGE ============================================
		@RequestMapping(value="/page",method=RequestMethod.GET)// verbos GET, POST, DELETE
		public ResponseEntity<Page<CategoriaDTO>> findPage(
				@RequestParam(value = "page", defaultValue = "0") Integer page, 
				@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
				@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
				@RequestParam(value = "direction", defaultValue = "ASC")String direction){
			Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<CategoriaDTO> listDto =  list.map(obj -> new CategoriaDTO(obj));
			return ResponseEntity.ok().body(listDto);

		}	
}
