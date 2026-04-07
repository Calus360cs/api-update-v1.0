package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.Cliente;
import com.app.confeitaria.docelivery.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        // Aqui salvamos usando o repositório específico de Cliente
        Cliente novoCliente = clienteRepository.save(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente dados) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(dados.getNome());
                    cliente.setTelefone(dados.getTelefone());

                    // Endereço
                    cliente.setEndereco(dados.getEndereco());
                    cliente.setBairro(dados.getBairro());
                    cliente.setCidade(dados.getCidade());
                    cliente.setUf(dados.getUf());
                    cliente.setCep(dados.getCep());

                    clienteRepository.save(cliente);
                    return ResponseEntity.ok("Perfil de cliente atualizado!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}