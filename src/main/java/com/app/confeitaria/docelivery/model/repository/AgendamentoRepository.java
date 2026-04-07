package com.app.confeitaria.docelivery.model.repository;

import com.app.confeitaria.docelivery.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {

}
