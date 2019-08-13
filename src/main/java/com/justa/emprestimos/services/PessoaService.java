package com.justa.emprestimos.services;

import com.justa.emprestimos.models.DTOs.PessoaDTO;
import com.justa.emprestimos.models.DTOs.PessoaFisicaDTO;
import com.justa.emprestimos.models.DTOs.PessoaJuridicaDTO;
import com.justa.emprestimos.models.Pessoa;
import com.justa.emprestimos.models.PessoaFisica;
import com.justa.emprestimos.models.PessoaJuridica;
import com.justa.emprestimos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    /**
     * Get individual by id
     * @param id
     * @return PessoaFisicaDTO
     */
    public PessoaFisicaDTO getIndividual(long id) {

        Optional<PessoaFisica> pessoa = pessoaRepository.obterPessoaFisica(id);

        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();

        if (pessoa.isPresent()) {
            pessoaFisicaDTO.setId(pessoa.get().getId());
            pessoaFisicaDTO.setNome(pessoa.get().getNome());
            pessoaFisicaDTO.setObs(pessoa.get().getObs());
            pessoaFisicaDTO.setApelido(pessoa.get().getApelido());
            pessoaFisicaDTO.setCpf(pessoa.get().getCpf());
            pessoaFisicaDTO.setExcluido(pessoa.get().isExcluido());
            pessoaFisicaDTO.setNacionalidade(pessoa.get().getNacionalidade());
            pessoaFisicaDTO.setNascimento(pessoa.get().getNascimento());
            pessoaFisicaDTO.setNaturalDe(pessoa.get().getNaturalDe());
            pessoaFisicaDTO.setProfissao(pessoa.get().getProfissao());
            pessoaFisicaDTO.setRg(pessoa.get().getRg());
            pessoaFisicaDTO.setRgExpedidoEm(pessoa.get().getRgExpedidoEm());
            pessoaFisicaDTO.setRgExpedidoPor(pessoa.get().getRgExpedidoPor());
            pessoaFisicaDTO.setSexo(pessoa.get().getSexo());
            pessoaFisicaDTO.setTratamento(pessoa.get().getTratamento());
            return pessoaFisicaDTO;
        } else {
            return null;
        }

    }

    /**
     * Get legal entity by id
     * @param id
     * @return PessoaJuridicaDTO
     */
    public PessoaJuridicaDTO getLegalEntity(long id) {

        Optional<PessoaJuridica> pessoa = pessoaRepository.obterPessoaJuridica(id);

        if (pessoa.isPresent()) {
            PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();

            pessoaJuridicaDTO.setId(pessoa.get().getId());
            pessoaJuridicaDTO.setNome(pessoa.get().getNome());
            pessoaJuridicaDTO.setObs(pessoa.get().getObs());
            pessoaJuridicaDTO.setAtividade(pessoa.get().getAtividade());
            pessoaJuridicaDTO.setAtividadeSecundaria(pessoa.get().getAtividadeSecundaria());
            pessoaJuridicaDTO.setCgf(pessoa.get().getCgf());
            pessoaJuridicaDTO.setCnpj(pessoa.get().getCnpj());
            pessoaJuridicaDTO.setExcluido(pessoa.get().isExcluido());
            pessoaJuridicaDTO.setFaturamento(pessoa.get().getFaturamento());
            pessoaJuridicaDTO.setFundacao(pessoa.get().getFundacao());
            pessoaJuridicaDTO.setQuantidadeFuncionarios(pessoa.get().getQuantidadeFuncionarios());
            pessoaJuridicaDTO.setRazaoSocial(pessoa.get().getRazaoSocial());

            return pessoaJuridicaDTO;
        } else {
            return null;
        }

    }

    /**
     * Get person by id
     * @param id
     * @return PessoaDTO
     * @throws Exception
     */
    public PessoaDTO getPersonById(long id) throws Exception {

        Pessoa pessoa = pessoaRepository.obterPessoa(id);

        PessoaFisicaDTO pessoaFisicaDTO = getIndividual(pessoa.getId());

        PessoaDTO novaPessoa = new PessoaDTO();

        novaPessoa.setId(pessoa.getId());
        novaPessoa.setNome(pessoa.getNome());
        novaPessoa.setObs(pessoa.getObs());
        novaPessoa.setCpfOuCnpj(pessoaFisicaDTO.getCpf());


        return novaPessoa;
    }

    /**
     * Create a new person
     * @param pessoa
     * @return
     */
    public Pessoa create(PessoaDTO pessoa) {

        Pessoa novaPessoa = null;


        if (pessoa.isPessoaFisica()) {
            novaPessoa = new PessoaFisica();
        } else {
            novaPessoa = new PessoaJuridica();
        }

        novaPessoa.setObs(pessoa.getObs());
        novaPessoa.setNome(pessoa.getNome());

        return update(novaPessoa);
    }

    /**
     * Update a new Person
     * @param pessoa
     * @return Pessoa
     */
    public Pessoa update(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    /**
     * Updates an individual
     * @param pessoa
     * @return Pessoa
     * @throws Exception
     */
    public Pessoa updateIndividual(PessoaFisicaDTO pessoa) throws Exception {

        PessoaFisica pessoaParaAtualizar = (PessoaFisica) pessoaRepository.findById(pessoa.getId())
                .orElseThrow(() -> new Exception("People not found"));

        if (pessoaParaAtualizar == null) {
            throw new Exception("Pessoa inválida");
        }

        // set os dados
        pessoaParaAtualizar.setNome(pessoa.getNome());
        pessoaParaAtualizar.setObs(pessoa.getObs());
        pessoaParaAtualizar.setExcluido(pessoa.isExcluido());
        pessoaParaAtualizar.setApelido(pessoa.getApelido());
        pessoaParaAtualizar.setCpf(pessoa.getCpf());
        pessoaParaAtualizar.setId(pessoa.getId());
        pessoaParaAtualizar.setNacionalidade(pessoa.getNacionalidade());
        pessoaParaAtualizar.setNascimento(pessoa.getNascimento());
        pessoaParaAtualizar.setNaturalDe(pessoa.getNaturalDe());
        pessoaParaAtualizar.setProfissao(pessoa.getProfissao());
        pessoaParaAtualizar.setRg(pessoa.getRg());
        pessoaParaAtualizar.setRgExpedidoEm(pessoa.getRgExpedidoEm());
        pessoaParaAtualizar.setRgExpedidoPor(pessoa.getRgExpedidoPor());
        pessoaParaAtualizar.setSexo(pessoa.getSexo());
        pessoaParaAtualizar.setTratamento(pessoa.getTratamento());

        return pessoaRepository.save(pessoaParaAtualizar);

    }

    /**
     * Update a legal entity
     * @param pessoa
     * @return Pessoa
     * @throws Exception
     */
    public Pessoa updateLegalEntity(PessoaJuridicaDTO pessoa) throws Exception {

        PessoaJuridica pessoaParaAtualizar = (PessoaJuridica) pessoaRepository.findById(pessoa.getId())
                .orElseThrow(() -> new Exception("People not found"));

        if (pessoaParaAtualizar == null) {
            throw new Exception("Invalid people");
        }

        // set os dados
        pessoaParaAtualizar.setNome(pessoa.getNome());
        pessoaParaAtualizar.setObs(pessoa.getObs());
        pessoaParaAtualizar.setExcluido(pessoa.isExcluido());
        pessoaParaAtualizar.setAtividade(pessoa.getAtividade());
        pessoaParaAtualizar.setAtividadeSecundaria(pessoa.getAtividadeSecundaria());
        pessoaParaAtualizar.setCgf(pessoa.getCgf());
        pessoaParaAtualizar.setCnpj(pessoa.getCnpj());
        pessoaParaAtualizar.setExcluido(pessoa.isExcluido());
        pessoaParaAtualizar.setFaturamento(pessoa.getFaturamento());
        pessoaParaAtualizar.setFundacao(pessoa.getFundacao());
        pessoaParaAtualizar.setQuantidadeFuncionarios(pessoa.getQuantidadeFuncionarios());
        pessoaParaAtualizar.setId(pessoa.getId());
        pessoaParaAtualizar.setRazaoSocial(pessoa.getRazaoSocial());

        return pessoaRepository.save(pessoaParaAtualizar);
    }

    /**
     * Get a person by cpf or cnpj
     * @param cpfOuCnpj
     * @return Long
     */
    public Long getPersonByCpfOrCnpj(String cpfOuCnpj) {
        return pessoaRepository.obtemPessoaPorCpfOuCnpj(cpfOuCnpj);
    }
}
