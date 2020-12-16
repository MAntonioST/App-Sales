package com.app.sales.domain;



import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table( name = "TB_ORDER_ITEM")
public class OrderItem extends DomainEntity  implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_ORDER_ITEM")
    private Integer id;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn( name = "ID_ORDER")
    private Order order;
	
	@ManyToOne
	@JoinColumn( name = "ID_PRODUCT")
    private Product product;
	
	@Column
    private Integer quantity;

}