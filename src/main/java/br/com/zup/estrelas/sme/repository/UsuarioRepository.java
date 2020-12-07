package br.com.zup.estrelas.sme.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Optional<Usuario> findByEmail(String email);
}
