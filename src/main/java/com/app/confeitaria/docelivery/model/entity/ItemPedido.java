package com.app.confeitaria.docelivery.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itemPedido")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false, length = 20)
    private int quantidade;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
    private Produto produto;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
}
