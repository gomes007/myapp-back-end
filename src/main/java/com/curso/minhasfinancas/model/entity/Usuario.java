package com.curso.minhasfinancas.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", schema = "financaspg")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	private String email;

	private String senha;
	
	/*
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "usuario")
	private List<Lancamento> lancamento = new ArrayList<>();
	*/

}

