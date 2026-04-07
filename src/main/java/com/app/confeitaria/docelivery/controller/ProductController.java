package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Produto;
import com.app.confeitaria.docelivery.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Resolve o erro: /api/products/offers
    @GetMapping("/offers")
    public ResponseEntity<List<Produto>> getOffers() {
        // Enquanto não criamos o campo promoção, vamos retornar os ativos
        return ResponseEntity.ok(produtoRepository.findByCodStatusTrue());
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        // Agora retornamos o objeto categoria completo que o frontend vai saber ler
        return ResponseEntity.ok(produtoRepository.findDistinctCategorias());
    }

    // GET /api/products - Lista todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    // GET /api/products/{id} - Detalhes de um doce específico
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}