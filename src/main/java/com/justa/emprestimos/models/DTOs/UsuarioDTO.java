package com.justa.emprestimos.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = -2539395972086386386L;

    private String username;

    private String password;

}
