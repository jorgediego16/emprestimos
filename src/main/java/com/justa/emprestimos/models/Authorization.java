package com.justa.emprestimos.models;

import lombok.Data;

@Data
public class Authorization {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private String expires_in;
}
