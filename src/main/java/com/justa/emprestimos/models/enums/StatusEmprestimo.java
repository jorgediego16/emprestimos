package com.justa.emprestimos.models.enums;

import lombok.Getter;

@Getter
public enum StatusEmprestimo {

    IN_PROGRESS(0, "In progress", "The loan is in progress"),
    APPROVED(1,"Approved", "The loan is approved"),
    DISAPPROVED(2, "Disapproved", "The loan is disapproved"),
    SUBMITTED(3,"Submitted", "The loan was submitted");

    private Integer id;
    private String name;
    private String description;

    StatusEmprestimo(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static StatusEmprestimo get (Integer id) {
        for (StatusEmprestimo statusEmprestimo : StatusEmprestimo.values()) {
            if (statusEmprestimo.getId().equals(id)) {
                return statusEmprestimo;
            }
        }
        return null;
    }

}
