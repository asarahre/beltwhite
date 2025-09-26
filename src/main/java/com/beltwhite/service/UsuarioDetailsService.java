package com.beltwhite.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beltwhite.exceptions.RecursoNaoEncontradosException;
import com.beltwhite.model.Usuario;
import com.beltwhite.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {

        Usuario usuario = this.usuarioRepository.findByEmail(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        try {

            List<GrantedAuthority> authorities = usuario.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNome().name())) // <- importante para
                                                                                              // spring interpretar os
                                                                                              // acessos
                    .collect(Collectors.toList());

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha())
                    .authorities(authorities) // agora pega do banco
                    .build();
        } catch (Exception e) {
            throw new RecursoNaoEncontradosException("erros login" + e.getMessage());
        }

    }
}
