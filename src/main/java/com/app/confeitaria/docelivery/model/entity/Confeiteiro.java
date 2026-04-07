package com.app.confeitaria.docelivery.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CONFEITEIRO")
@Getter
@Setter


public class Confeiteiro extends Usuario {
    @Column(name = "nome_loja", nullable = true)
    private String nomeLoja;
    @Column(name = "cnpj", nullable = true)
    private String cnpj;
    @Column(name = "proprietario", nullable = true)
    private String proprietario;
    @Column(nullable = true)
    private String categoria;
    @Column(name = "promocao", nullable = true)
    private Boolean promocao; // Mude para Boolean (com B maiúsculo) para aceitar nulo

}
