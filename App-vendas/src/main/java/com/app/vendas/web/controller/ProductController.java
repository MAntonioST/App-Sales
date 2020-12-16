package com.app.vendas.web.controller;



import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.app.vendas.domain.Product;





@RestController
@RequestMapping("/api/produtos")
public class ProductController extends BaseController<Product, String>{

	
	
	public ProductController() {
		super(Product.class);
	}

	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<?> saveProduct(@RequestBody Product aEntity) {
		   
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
	public  @ResponseBody ResponseEntity<?> productById(@PathVariable Integer id) {
		
		Product aEntity = new Product();
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
	public  @ResponseBody ResponseEntity<?> findFiltro(Product filtro) {
		 
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
