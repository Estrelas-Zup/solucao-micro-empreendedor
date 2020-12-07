package br.com.zup.estrelas.sme.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.sme.entity.Usuario;
import br.com.zup.estrelas.sme.repository.UsuarioRepository;

@Service
public class AutenticacaoServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
