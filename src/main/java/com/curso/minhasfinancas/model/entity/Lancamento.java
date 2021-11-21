package com.curso.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.curso.minhasfinancas.model.enums.StatusLancamento;
import com.curso.minhasfinancas.model.enums.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "lancamento", schema = "financaspg")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
	private Long id;
	
	private String descricao;
	
	private Integer ano;
	
	private Integer mes;
	
	private BigDecimal valor;
	
	
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	
	@ManyToOne()
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipo;
	
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento status;
	
}
