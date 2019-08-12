package com.justa.emprestimos.services;

import com.justa.emprestimos.models.DTOs.UsuarioDTO;
import com.justa.emprestimos.models.Usuario;
import com.justa.emprestimos.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * The purpose of this method is to register a user
     * @param usuarioDTO
     * @return Usuario
     */
    public Usuario registerUser (UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // PasswordEncoder is used to encrypt the password
        usuario.setStatusCadastro(0);

        return usuarioRepository.save(usuario);
    }

}
