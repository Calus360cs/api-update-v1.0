package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Confeiteiro;
import com.app.confeitaria.docelivery.model.repository.ConfeiteiroRepository;
import com.app.confeitaria.docelivery.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confeiteiro")
@CrossOrigin("*")
public class ConfeiteiroController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ConfeiteiroRepository confeiteiroRepository;

    @PostMapping
    public ResponseEntity<Confeiteiro> cadastrar(@RequestBody Confeiteiro confeiteiro) {
        Confeiteiro novoConfeiteiro = usuarioService.salvarConfeiteiro(confeiteiro);
        return new ResponseEntity<>(novoConfeiteiro, HttpStatus.CREATED);
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Confeiteiro>> listarPorCidade(@PathVariable String cidade) {
        List<Confeiteiro> lojas = usuarioService.listarLojasPorCidade(cidade);
        return ResponseEntity.ok(lojas);
    }

    @Transactional
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Confeiteiro> atualizarPerfil(@PathVariable Long id, @RequestBody Confeiteiro dadosAtualizados) {
        // 1. Use o nome da variável do seu repositório (geralmente começa com letra minúscula)
        return confeiteiroRepository.findById(id)
                .map(registro -> {
                    // Atualiza os campos específicos do Confeiteiro
                    registro.setNomeLoja(dadosAtualizados.getNomeLoja());
                    registro.setCnpj(dadosAtualizados.getCnpj());

                    // Atualiza os campos herdados de Usuario
                    registro.setTelefone(dadosAtualizados.getTelefone());
                    registro.setEndereco(dadosAtualizados.getEndereco());
                    registro.setBairro(dadosAtualizados.getBairro());
                    registro.setCidade(dadosAtualizados.getCidade());
                    registro.setUf(dadosAtualizados.getUf());
                    registro.setCep(dadosAtualizados.getCep());

                    // 2. Salva usando a mesma variável 'repository'
                    Confeiteiro atualizado = confeiteiroRepository.save(registro);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}