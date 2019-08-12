package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.DTOs.UsuarioDTO;
import com.justa.emprestimos.services.LoginService;
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
@Api(value = "Módulo de login")
@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Returns true if login is successful and false otherwise
     * @param usuarioDTO
     * @return ResponseEntity<Boolean>
     */
    @ApiOperation(value = "Login method", response = Boolean.class)
    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> login (UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(loginService.login(usuarioDTO), HttpStatus.OK);
    }
}
