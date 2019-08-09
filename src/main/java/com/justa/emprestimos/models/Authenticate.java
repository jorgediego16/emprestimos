package com.justa.emprestimos.models;

import lombok.Data;

@Data
public class Authenticate {

    private String grant_type;

    private String username;

    private String password;
}
