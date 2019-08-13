package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.DTOs.EmprestimoDTO;
import com.justa.emprestimos.models.Emprestimo;
import com.justa.emprestimos.services.EmprestimoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Módulo de empréstimos")
@RestController
@RequestMapping(value = "/api/v1/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    /**
     * The purpose of this endpoint is to register a new loan
     * @param emprestimoDTO
     * @return ResponseEntity<Emprestimo>
     */
    @ApiOperation(value = "Save a loan", response = Emprestimo.class)
    @PostMapping(value = "/register-loan")
    public ResponseEntity<Emprestimo> saveLoan (@RequestParam EmprestimoDTO emprestimoDTO) throws Exception {
        return new ResponseEntity<>(emprestimoService.saveLoan(emprestimoDTO), HttpStatus.OK);
    }

    /**
     * Get loans by id status
     * @param idStatus
     * @return ResponseEntity<List<Emprestimo>>
     */
    @ApiOperation(value = "Save a loan", response = Emprestimo.class)
    @PostMapping(value = "/get-loan-status/{idStatus}")
    public ResponseEntity<List<Emprestimo>> getLoanByStatus (@PathVariable Integer idStatus) {
        return new ResponseEntity<>(emprestimoService.getLoanByStatus(idStatus), HttpStatus.OK);
    }
}
