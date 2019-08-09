package com.justa.emprestimos.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Api(value = "Módulo de usuários")
@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {
}
