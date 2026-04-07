package com.app.confeitaria.docelivery.model.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table (name ="agendamento")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dataHoraAgendamento;
    @Column(nullable = false, length = 100)
    private String descricao;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
    private Pedido pedido;
}
