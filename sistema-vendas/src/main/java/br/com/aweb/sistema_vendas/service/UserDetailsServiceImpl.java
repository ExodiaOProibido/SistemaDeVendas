package br.com.aweb.sistema_vendas.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.com.aweb.sistema_vendas.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Tentando carregar usuário: {}", username);
        
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado: {}", username);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });

        log.info("Usuário encontrado: {}", user.getUsername());
        log.info("Roles do usuário: {}", user.getRoles().size());

        var authorities = user.getRoles().stream()
                .map(role -> {
                    String authority = "ROLE_" + role.getNome();
                    log.info("Granted Authority: {}", authority);
                    return new SimpleGrantedAuthority(authority);
                })
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}