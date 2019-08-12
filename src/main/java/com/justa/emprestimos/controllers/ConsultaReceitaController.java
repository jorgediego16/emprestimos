package com.justa.emprestimos.controllers;

import com.justa.emprestimos.services.ConsultaReceitaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(value = "Cnpj Query Module")
@RestController
@RequestMapping(value = "/api/v1/consulta")
public class ConsultaReceitaController {

    @Autowired
    ConsultaReceitaService consultaReceitaService;

    /**
     * Returns cnpj data entered
     * @param cnpj
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "Returns cnpj data entered", response = String.class)
    @GetMapping(value = "/consulta-cnpj/{cnpj}")
    public ResponseEntity<String> consultaCnpj (@PathVariable String cnpj) {
        return new ResponseEntity<>(consultaReceitaService.consultaCnpj(cnpj), HttpStatus.OK);
    }
}
