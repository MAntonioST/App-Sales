package com.app.sales.core.dto;




import java.io.Serializable;
import java.math.BigDecimal;

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
public class OrderItemDTO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String productDescription;
    private BigDecimal unitPrice;
    private Integer quantity;
    
	
}