package com.justa.emprestimos.services;

import com.justa.emprestimos.models.Authenticate;
import com.justa.emprestimos.models.Authorization;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class AuthorizationService {

    private WebClient webClient;

    private static final String URL_TOKEN = "http://localhost:3333/oauth/token";

    public AuthorizationService () {this.webClient = WebClient.builder().build();}

    public Authorization obterToken (Authenticate authenticate) {
        WebClient.RequestHeadersSpec<?>
                request = webClient
                .post()
                .uri(URL_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromObject(authenticate));
        return Objects.requireNonNull(request.retrieve().bodyToMono(Authorization.class).block());
    }
}
