package br.com.aweb.sistema_vendas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public enum RoleName {
        ADMIN,
        USER,
        VENDEDOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private RoleName nome;

    public String getAuthority() {
        return "ROLE_" + nome.name();
    }
}