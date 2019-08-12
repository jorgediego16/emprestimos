package com.justa.emprestimos.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class ConsultaReceitaService {

    private WebClient webClient;

    public ConsultaReceitaService () {
        this.webClient = WebClient.builder().build();
    }

    private static final String URI_CONSULTA_RECEITA = "https://www.receitaws.com.br/v1/cnpj/{cnpj}";

    /**
     * Returns cnpj data entered
     * @param cnpj
     * @return String
     */
    public String consultaCnpj (String cnpj) {
        WebClient.RequestHeadersSpec<?>
                request = webClient
                .get()
                .uri(URI_CONSULTA_RECEITA, cnpj)
                .accept(MediaType.ALL);
        return Objects.requireNonNull(request.retrieve().bodyToMono(String.class).block());
    }

}
