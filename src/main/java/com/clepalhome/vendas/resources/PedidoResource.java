package com.clepalhome.vendas.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clepalhome.vendas.domain.Pedido;
import com.clepalhome.vendas.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
		
//===================================== LISTAR =============================================
		@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
		public ResponseEntity<Pedido> find(@PathVariable Integer id){
			Pedido obj = service.find(id);
			return ResponseEntity.ok().body(obj);
	}

//==================================== INSERIR =============================================
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
			
		}

}
