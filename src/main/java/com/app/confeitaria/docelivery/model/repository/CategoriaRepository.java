package com.app.confeitaria.docelivery.model.repository;

import com.app.confeitaria.docelivery.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
