package com.app.sales.domain;




import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
@Table( name = "TB_CUSTOMER")
public class Customer extends  DomainEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_CUSTOMER")
    private Integer id;

    @Column( name = "NAME", length = 100)
    private String name;
    
    @Column( name = "CPF", length = 14)
    private String cpf;
        

	@JsonIgnore
    @OneToMany( mappedBy = "customer", fetch = FetchType.LAZY) 
    private Set<Order> orders;


	public Customer(Integer id) {
		super();
		this.id = id;
	}
 
	
    	
}


