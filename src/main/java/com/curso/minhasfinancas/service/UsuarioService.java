package com.curso.minhasfinancas.service;

import java.util.Optional;

import com.curso.minhasfinancas.model.entity.Usuario;


public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);
	
	void deletar(Usuario usuario);

}
