package com.app.sales.web.controller;



import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.sales.domain.Customer;

import io.swagger.annotations.*;





@RestController
@RequestMapping("/api/customers")
@Api("Api Clientes")
public class CustomerController extends BaseController<Customer, String>{

	
	public CustomerController() {
		super(Customer.class);
	}

	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
	public  ResponseEntity<?> save(@RequestBody Customer aEntity) {
		   
		    result = this.appFacade.save(aEntity);
			Optional<?> entity = Optional.ofNullable(result.getEntity());
			if (entity.isPresent() && Stream.of(entity.get()).count() > 0)
				return ResponseEntity.ok(entity.get());
			else if(result.hasMsg())
				return ResponseEntity.badRequest().body(result.getMsg());
			else
				return ResponseEntity.noContent().build();

	}
	
	@PostMapping("{id}")
	 @ApiOperation("Obter detalhes de um cliente")
     @ApiResponses({
        @ApiResponse(code = 200, message = "Cliente encontrado"),
        @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado")
    })
	public  ResponseEntity<?> getClienteById(@PathVariable  Integer id) {
		 
		Customer aEntity = new Customer();
		aEntity.setId(id);
		result = this.appFacade.find(aEntity);
		Optional<?> entity = Optional.ofNullable(result.getEntity());
		if (entity.isPresent() && Stream.of(entity.get()).count() > 0)
			return ResponseEntity.ok(entity.get());
		else if(result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		else
			return ResponseEntity.noContent().build();
	

	}
	
	@PostMapping("findfiltro")
	@ApiOperation("Filtro de  cliente")
    @ApiResponses({
       @ApiResponse(code = 200, message = "Cliente  encontrado"),
       @ApiResponse(code = 404, message = "Cliente não encontrado para parametro informado")
   })
	public  ResponseEntity<?> find(Customer filtro) {
		 
		result = this.appFacade.findAll(filtro);
		if (result.hasMsg())
			return ResponseEntity.badRequest().body(result.getMsg());
		if (result.hasEntities()) {
			return ResponseEntity.ok().body(result.getEntities());
		} else if (result.hasEntity()) {
			return ResponseEntity.ok().body(result.getEntity());
		} else
			return ResponseEntity.noContent().build();
		
		
	}

	
}
