package br.com.zup.estrelas.sme.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
      

 

}
