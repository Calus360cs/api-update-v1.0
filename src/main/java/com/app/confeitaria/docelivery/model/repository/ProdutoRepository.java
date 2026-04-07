package com.app.confeitaria.docelivery.model.repository;

import com.app.confeitaria.docelivery.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca produtos ativos (esse campo codStatus existe na sua classe Produto)
    List<Produto> findByCodStatusTrue();

    // Esta versão busca o objeto Categoria inteiro para evitar erro de nome de campo
    @Query("SELECT DISTINCT p.categoria FROM Produto p")
    List<Object> findDistinctCategorias();
}