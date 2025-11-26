package br.com.aweb.sistema_vendas.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.aweb.sistema_vendas.model.Role;
import br.com.aweb.sistema_vendas.model.User;
import br.com.aweb.sistema_vendas.repository.UserRepository;
import br.com.aweb.sistema_vendas.repository.RoleRepository;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        return args -> {

            // Se não houver roles, cria ADMIN e USER
            if (roleRepo.count() == 0) {
                roleRepo.save(new Role(null, "ADMIN"));
                roleRepo.save(new Role(null, "USER"));
            }

            // Cria o usuário admin apenas se não existir nenhum usuário
            if (userRepo.count() == 0) {

                // findByNome retorna Optional<Role>, então usamos orElseThrow
                Role adminRole = roleRepo.findByNome("ADMIN")
                        .orElseThrow(() -> new RuntimeException("Role ADMIN não encontrado"));

                User user = new User(
                    "admin",
                    encoder.encode("admin123"),
                    Set.of(adminRole)
                );

                userRepo.save(user);
            }
        };
    }
}
