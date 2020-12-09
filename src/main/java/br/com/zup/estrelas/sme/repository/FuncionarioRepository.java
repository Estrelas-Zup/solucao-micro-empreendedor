package br.com.zup.estrelas.sme.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.sme.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    
    boolean existsByCpf(String cpf);
}
