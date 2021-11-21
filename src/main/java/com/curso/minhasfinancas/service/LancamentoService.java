package com.curso.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.curso.minhasfinancas.model.entity.Lancamento;
import com.curso.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
		
	List<Lancamento> buscar (Lancamento lancamentoFiltro);
	
	void deletar (Lancamento lancamento);
	
	void validar (Lancamento lancamento);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	Optional<Lancamento> obterPorId(Long id);
	
	BigDecimal obterSaldoPorUsuario(Long id);	
	

}
