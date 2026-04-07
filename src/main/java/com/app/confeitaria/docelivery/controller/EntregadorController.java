package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Entregador;
import com.app.confeitaria.docelivery.model.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregadores")
@CrossOrigin("*")
public class EntregadorController {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @PostMapping
    public ResponseEntity<Entregador> cadastrar(@RequestBody Entregador entregador) {
        Entregador novoEntregador = entregadorRepository.save(entregador);
        return new ResponseEntity<>(novoEntregador, HttpStatus.CREATED);
    }

    @GetMapping("/veiculo/{tipo}")
    public List<Entregador> listarPorVeiculo(@PathVariable String tipo) {
        return entregadorRepository.findByVeiculoIgnoreCase(tipo);
    }

    @GetMapping
    public List<Entregador> listarTodos() {
        return entregadorRepository.findAll();
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<?> atualizarEntregador(@PathVariable Long id, @RequestBody Entregador dados) {
        return entregadorRepository.findById(id)
                .map(entregador -> {
                    // Campos de Usuário
                    entregador.setNome(dados.getNome());
                    entregador.setTelefone(dados.getTelefone());

                    // Campos de Endereço
                    entregador.setEndereco(dados.getEndereco());
                    entregador.setBairro(dados.getBairro());
                    entregador.setCidade(dados.getCidade());
                    entregador.setUf(dados.getUf());
                    entregador.setCep(dados.getCep());

                    // Campos específicos do Entregador
                    entregador.setVeiculo(dados.getVeiculo());
                    entregador.setPlacaVeiculo(dados.getPlacaVeiculo());

                    entregadorRepository.save(entregador);
                    return ResponseEntity.ok("Perfil de entregador atualizado!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}