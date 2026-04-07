package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Admin;
import com.app.confeitaria.docelivery.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // Criar um novo administrador
    @PostMapping
    public ResponseEntity<Admin> cadastrar(@RequestBody Admin admin) {
        Admin novoAdmin = adminRepository.save(admin);
        return new ResponseEntity<>(novoAdmin, HttpStatus.CREATED);
    }

    // Listar todos os administradores
    @GetMapping
    public List<Admin> listarTodos() {
        return adminRepository.findAll();
    }

    // Buscar administradores por nível de acesso (ex: MASTER, SUPORTE)
    @GetMapping("/nivel/{nivel}")
    public List<Admin> buscarPorNivel(@PathVariable String nivel) {
        return adminRepository.findByNivelAcesso(nivel);
    }

    // Buscar um admin específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarPorId(@PathVariable Long id) {
        return adminRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!adminRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adminRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}