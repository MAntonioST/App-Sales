package com.app.vendas.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table( name = "TB_USER")
public class User extends DomainEntity  implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID_USER")
    private Integer id;
	
	 @Column( name = "USERNAME")
	private String username;
	
	 @Column( name = "PASSWORD")
	private String password;
	
	 @Column( name = "ADMIN")
	private boolean admin;
	
}
