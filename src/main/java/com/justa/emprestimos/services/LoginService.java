package com.justa.emprestimos.services;

import com.justa.emprestimos.models.DTOs.UsuarioDTO;
import com.justa.emprestimos.models.Usuario;
import com.justa.emprestimos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Returns true if login is successful and false otherwise
     * @param usuarioDTO
     * @return Boolean
     */
    public boolean login (UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioRepository.getUser(usuarioDTO.getUsername());

        return passwordEncoder.matches(usuarioDTO.getPassword(), usuario.getPassword());
    }

}
