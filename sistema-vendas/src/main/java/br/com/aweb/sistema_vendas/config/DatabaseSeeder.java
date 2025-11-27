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
            System.out.println("=== INICIANDO DATABASE SEEDER ===");

            Role adminRole = roleRepo.findByNome(Role.RoleName.ADMIN)
                .orElseGet(() -> {
                    System.out.println("Criando role ADMIN...");
                    Role role = new Role();
                    role.setNome(Role.RoleName.ADMIN);
                    return roleRepo.save(role);
                });

            roleRepo.findByNome(Role.RoleName.USER)
                .orElseGet(() -> {
                    System.out.println("Criando role USER...");
                    Role role = new Role();
                    role.setNome(Role.RoleName.USER);
                    return roleRepo.save(role);
                });

            if (userRepo.findByUsername("admin").isEmpty()) {
                System.out.println("Criando usuário admin...");
                
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123")); 
                admin.setRoles(Set.of(adminRole));
                
                User savedUser = userRepo.save(admin);
                System.out.println(" Usuário admin criado com sucesso!");
                System.out.println("   Username: " + savedUser.getUsername());
                System.out.println("   Password: admin123 (codificada)");
                System.out.println("   Roles: " + savedUser.getRoles().size());
            } else {
                System.out.println(" Usuário admin já existe");
            }

            System.out.println("=== DATABASE SEEDER FINALIZADO ===");
        };
    }
}