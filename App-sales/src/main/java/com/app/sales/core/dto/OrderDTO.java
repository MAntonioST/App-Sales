package com.app.sales.core.dto;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
public class OrderDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer code;
    private String cpf;
    private String nameCustomer;
    private BigDecimal total;
    private String orderDate;
    private String status;
    private List<OrderItemDTO> items;
    	
	
}