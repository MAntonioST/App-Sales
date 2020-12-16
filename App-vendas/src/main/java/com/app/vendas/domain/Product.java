package com.app.vendas.domain;




import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table( name = "TB_PRODUCT")
public class Product extends DomainEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_PRODUCT")
    private Integer id;
	
	@Column( name = "DESCRIPTION")
    private String description;
	 
	@Column( name = "PRICE",precision = 20, scale = 2)
    private BigDecimal price;

	public Product(Integer id) {
		super();
		this.id = id;
	}

	
	
}