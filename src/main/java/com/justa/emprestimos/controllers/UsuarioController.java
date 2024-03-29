package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.DTOs.UsuarioDTO;
import com.justa.emprestimos.models.Usuario;
import com.justa.emprestimos.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Api(value = "Módulo de usuários")
@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * The purpose of this endpoint is to register a user
     * @param usuario
     * @return ResponseEntity<Usuario>
     */
    @ApiOperation(value = "Save a user", response = Usuario.class)
    @PostMapping(value = "/register-user")
    public ResponseEntity<Usuario> registerUser (UsuarioDTO usuario) {
        return new ResponseEntity<>(usuarioService.registerUser(usuario), HttpStatus.OK);
    }

}
