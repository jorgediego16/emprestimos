package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.DTOs.UsuarioDTO;
import com.justa.emprestimos.models.Usuario;
import com.justa.emprestimos.services.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> login (UsuarioDTO usuarioDTO) throws Exception {
        return new ResponseEntity<>(loginService.login(usuarioDTO), HttpStatus.OK);
    }

    /**
     * This method allows the user to recover their password
     * @param username
     * @param newPassword
     * @return ResponseEntity<Usuario>
     * @throws Exception
     */
    @ApiOperation(value = "Recovery password method", response = Usuario.class)
    @PutMapping(value = "/recovery")
    public ResponseEntity<Usuario> recoveryPassword (@RequestParam String username, @RequestParam String newPassword) throws Exception {
        return new ResponseEntity<>(loginService.passwordRecovery(username, newPassword), HttpStatus.OK);
    }
}
