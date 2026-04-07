package com.app.confeitaria.docelivery.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 10)
    private double preco;
    @Column(nullable = false, length = 20)
    private int estoque;
    @Column(nullable = false, length = 100)
    private String descricao;
    private boolean codStatus;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

}
