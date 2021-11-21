package com.curso.minhasfinancas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.minhasfinancas.dto.LancamentoDTO;
import com.curso.minhasfinancas.exception.RegraNegocioException;
import com.curso.minhasfinancas.model.entity.Lancamento;
import com.curso.minhasfinancas.model.entity.Usuario;
import com.curso.minhasfinancas.model.enums.StatusLancamento;
import com.curso.minhasfinancas.model.enums.TipoLancamento;
import com.curso.minhasfinancas.service.LancamentoService;
import com.curso.minhasfinancas.service.UsuarioService;

@SuppressWarnings({ "rawtypes", "unchecked" })


@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService service;
	
	@Autowired
	private UsuarioService usuarioService;
	

		
	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {


		try {
			Lancamento entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id ,@RequestBody LancamentoDTO dto) {
		return service.obterPorId(id).map(entity -> {
			try {
				Lancamento lancamento = converter(dto);
				lancamento.setId(entity.getId());
				service.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Lancamento nao encontrado na base", HttpStatus.BAD_REQUEST));				
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("lancamento nao encontrado", HttpStatus.BAD_REQUEST));						
	}
	
	
	
	//para salvar pega da entidade dto e joga na entidade lancamento 
	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService.obterPorId(dto.getUsuario()).orElseThrow(() -> new RegraNegocioException("usuario nao encontrado para id informado!"));		
		lancamento.setUsuario(usuario);
		
		if (dto.getTipo() != null) {
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		}
		
		if (dto.getStatus() != null) {
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		}
		
		return lancamento;
		
	}
	
	
	
	//para atualizar pega da entidade lancamento e joga no DTO  
	private LancamentoDTO converter(Lancamento lancamento) {
		return LancamentoDTO.builder()
									  .id(lancamento.getId())
									  .descricao(lancamento.getDescricao())
									  .ano(lancamento.getAno())
									  .mes(lancamento.getMes())
									  .valor(lancamento.getValor())
									  .status(lancamento.getStatus().name())
									  .tipo(lancamento.getTipo().name())
									  .usuario(lancamento.getUsuario().getId())
									  .build();						  																	  
	}
	
	
	
	
	

}