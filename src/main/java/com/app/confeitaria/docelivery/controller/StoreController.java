package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Confeiteiro;
import com.app.confeitaria.docelivery.model.repository.ConfeiteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin("*")
public class StoreController {

    @Autowired
    private ConfeiteiroRepository confeiteiroRepository;

    // GET /api/stores - Retorna todas as lojas (confeiteiros)
    @GetMapping
    public ResponseEntity<List<Confeiteiro>> listarLojas() {
        List<Confeiteiro> lojas = confeiteiroRepository.findAll();
        return ResponseEntity.ok(lojas);
    }

    // GET /api/stores/{id} - Retorna uma loja específica
    @GetMapping("/{id}")
    public ResponseEntity<Confeiteiro> buscarLojaPorId(@PathVariable Long id) {
        return confeiteiroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}