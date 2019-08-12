package com.justa.emprestimos.models.enums;

import lombok.Getter;

@Getter
public enum StatusCadastroUsuario {

    IN_PROGRESS(0, "In progress", "Your registration is under review"),
    APPROVED(1,"Approved", "Your registration has been approved"),
    DISAPPROVED(2, "Disapproved", "Your registration has been disapproved");

    private Integer id;
    private String name;
    private String description;

    StatusCadastroUsuario(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static StatusCadastroUsuario get (Integer id) {
        for (StatusCadastroUsuario statusCadastroUsuario : StatusCadastroUsuario.values()) {
            if (statusCadastroUsuario.getId().equals(id)) {
                return statusCadastroUsuario;
            }
        }
        return null;
    }

}
