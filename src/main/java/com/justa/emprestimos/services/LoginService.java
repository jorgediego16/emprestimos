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
    public boolean login (UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuario = usuarioRepository.getUser(usuarioDTO.getUsername());

        if (usuario == null) {
            throw new Exception("User not found");
        }

        return passwordEncoder.matches(usuarioDTO.getPassword(), usuario.getPassword());
    }

    /**
     * This method allows the user to recover their password
     * @param username
     * @param newPassword
     * @return Usuario
     * @throws Exception
     */
    public Usuario passwordRecovery (String username, String newPassword) throws Exception {

        Usuario usuario = usuarioRepository.getUser(username);

        if (usuario == null) {
            throw new Exception("User not found");
        }

        usuario.setPassword(passwordEncoder.encode(newPassword));

        return usuarioRepository.save(usuario);
    }

}
