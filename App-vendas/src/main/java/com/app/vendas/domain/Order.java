package com.app.vendas.domain;





import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.app.vendas.domain.enums.OrderStatus;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
@Entity
@Table( name = "TB_ORDER")
public class Order extends DomainEntity  implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_ORDER")
    private Integer id;
	

	@ManyToOne
	@JoinColumn( name = "ID_CUSTOMER")
    private Customer customer;
	 
	@Column( name = "ORDER_DATE")
    private LocalDate OrderDate;
	 
	@Column( name = "TOTAL", precision = 20, scale = 2)
    private BigDecimal total;
	
	
	@OneToMany( mappedBy = "order")
	private List<OrderItem> items ;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS")
	private OrderStatus status;
	

	public Order(Integer id) {
		super();
		this.id = id;
	}
	
//	public List<OrderItem> getItems() {
//        return items == null ? new ArrayList<>() : items;
//    }
	
}