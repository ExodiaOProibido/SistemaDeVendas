package br.com.aweb.sistema_vendas.repository;

import br.com.aweb.sistema_vendas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByNome(Role.RoleName nome);
}