package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.DTOs.PessoaDTO;
import com.justa.emprestimos.models.DTOs.PessoaFisicaDTO;
import com.justa.emprestimos.models.DTOs.PessoaJuridicaDTO;
import com.justa.emprestimos.models.Pessoa;
import com.justa.emprestimos.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "*")
@Api(value = "Controller de Pessoas")
@RestController
@RequestMapping(value = "/api/v1/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * Get an individual by id
     * @param id
     * @return ResponseEntity<PessoaFisicaDTO>
     */
    @GetMapping(value = "/get-individual/{id}")
    @ApiOperation(value = "Get an individual by id", response = PessoaFisicaDTO.class)
    public ResponseEntity<PessoaFisicaDTO> getIndividual(@PathVariable long id) {
        return new ResponseEntity<>(pessoaService.getIndividual(id), HttpStatus.OK);
    }

    /**
     * Get legal entity by id
     * @param id
     * @return ResponseEntity<PessoaJuridicaDTO>
     */
    @GetMapping(value = "/get-legal-entity/{id}")
    @ApiOperation(value = "Get an legal entity by id", response = PessoaJuridicaDTO.class)
    public ResponseEntity<PessoaJuridicaDTO> getLegalEntity(@PathVariable long id) {
        return new ResponseEntity<>(pessoaService.getLegalEntity(id), HttpStatus.OK);
    }

    /**
     * Get an person by id
     * @param id
     * @return ResponseEntity<PessoaDTO>
     * @throws Exception
     */
    @GetMapping(value = "/get-person/{id}")
    @ApiOperation(value = "Get person by id", response = Pessoa.class)
    public ResponseEntity<PessoaDTO> getPersonById(@PathVariable long id) throws Exception {
        return new ResponseEntity<>(pessoaService.getPersonById(id), HttpStatus.OK);
    }

    /**
     * Create an person
     * @param pessoaDTO
     * @return ResponseEntity<Long>
     */
    @PostMapping(path = "/create-person")
    @ApiOperation(value = "Create an person", response = String.class)
    public ResponseEntity<Long> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {

        Pessoa novaPessoa = pessoaService.create(pessoaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaPessoa.getId())
                .toUri();

        return new ResponseEntity<>(novaPessoa.getId(), HttpStatus.CREATED);
    }

    /**
     * Update individual
     * @param pessoa
     * @return ResponseEntity<Pessoa>
     * @throws Exception
     */
    @ApiOperation(value = "Update indvidual", response = Pessoa.class)
    @PutMapping(path = "/update-individual")
    public ResponseEntity<Pessoa> updateIndividual(@RequestBody PessoaFisicaDTO pessoa) throws Exception {
        return new ResponseEntity<>(pessoaService.updateIndividual(pessoa), HttpStatus.OK);
    }

    /**
     * Update legal entity
     * @param pessoa
     * @return ResponseEntity<Pessoa>
     * @throws Exception
     */
    @ApiOperation(value = "Update an legal entity", response = Pessoa.class)
    @PutMapping(path = "/update-legal-entity")
    public ResponseEntity<Pessoa> updateLegalEntity(@RequestBody PessoaJuridicaDTO pessoa) throws Exception {
        return new ResponseEntity<>(pessoaService.updateLegalEntity(pessoa), HttpStatus.OK);
    }

    /**
     * Get a person by cpf or cnpj
     * @param cpfOuCnpj
     * @return
     */
    @ApiOperation(value = "Get a person by cpf or cnpj", response = Long.class)
    @GetMapping(value = "/get-person-cpf-cnpj/{cpfOuCnpj}")
    public ResponseEntity<Long> getPersonByCpfOrCnpj (@PathVariable String cpfOuCnpj) {
        return new ResponseEntity<>(pessoaService.getPersonByCpfOrCnpj(cpfOuCnpj), HttpStatus.OK);
    }
}
