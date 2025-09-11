package com.beltwhite.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beltwhite.model.Usuario;
import com.beltwhite.repository.UsuarioRepository;
import com.beltwhite.security.JwtUtil;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        String senhaCriptografada = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return this.usuarioRepository.save(usuario);
    }

    public Map<String, String> login(String email, String passwordDescript) {

        Optional<Usuario> usuario = buscarPorEmail(email);

        if (usuario.isPresent() && passwordEncoder.matches(passwordDescript, usuario.get().getSenha())) {
            String token = JwtUtil.generateToken(usuario.get().getEmail());
            return Map.of("token", token);
        } else {
            return null;
        }

    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }
}
