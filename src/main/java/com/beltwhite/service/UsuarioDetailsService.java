package com.beltwhite.service;

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
            String[] roles = new String[] {};
            try {

                roles = usuario.getRoles()
                        .stream()
                        .map(r -> r.getNome()) // supondo que Role tem campo 'nome' = "ADMIN", "USER", etc.
                        .toArray(String[]::new);
            } catch (Exception e) {
                // TODO: handle exception
            }

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha())
                    .roles(roles) // agora pega do banco
                    .build();
        } catch (Exception e) {
            throw new RecursoNaoEncontradosException("erros login");
        }

    }
}
