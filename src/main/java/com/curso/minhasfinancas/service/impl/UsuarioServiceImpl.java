package com.curso.minhasfinancas.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.minhasfinancas.exception.ErroAutenticacao;
import com.curso.minhasfinancas.exception.RegraNegocioException;
import com.curso.minhasfinancas.model.entity.Usuario;
import com.curso.minhasfinancas.model.repository.UsuarioRepository;
import com.curso.minhasfinancas.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("usuario não encontrado");
		}

		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("senha invalida!");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());		
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("ja existe usuario cadastrado com esse email!");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public void deletar(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		repository.delete(usuario);		
	}

}
