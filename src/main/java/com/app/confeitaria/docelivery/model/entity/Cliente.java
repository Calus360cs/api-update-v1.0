package com.app.confeitaria.docelivery.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CLIENTE")
@Getter
@Setter

public class Cliente extends Usuario {

    @Column(nullable = true, length = 20)
    private String apelido;
}
