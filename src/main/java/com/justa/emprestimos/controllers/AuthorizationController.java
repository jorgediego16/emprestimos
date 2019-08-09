package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.Authenticate;
import com.justa.emprestimos.models.Authorization;
import com.justa.emprestimos.services.AuthorizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(value = "Módulo de autenticação")
@RestController
@EnableResourceServer
@EnableAuthorizationServer
@RequestMapping(value = "/api/v1/authorization")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping(path = "/obter-token")
    @ApiOperation(value = "Obter token", response = Authorization.class)
    public ResponseEntity<Authorization> obterToken (@RequestBody Authenticate authenticate) {
        return new ResponseEntity<>(authorizationService.obterToken(authenticate), HttpStatus.OK);
    }

}
