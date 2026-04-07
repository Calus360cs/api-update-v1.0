package com.app.confeitaria.docelivery.controller;

import com.app.confeitaria.docelivery.model.entity.*;
import com.app.confeitaria.docelivery.model.enums.TipoUsuario;
import com.app.confeitaria.docelivery.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private final ConfeiteiroRepository confeiteiroRepository;

    @Autowired
    private final EntregadorRepository entregadorRepository;

    public AuthController(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, ConfeiteiroRepository confeiteiroRepository, EntregadorRepository entregadorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.confeiteiroRepository = confeiteiroRepository;
        this.entregadorRepository = entregadorRepository;
    }

    @PostMapping("/cadastro/cliente")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        cliente.setTipoUsuario(TipoUsuario.CLIENTE); // Define o tipo internamente
        cliente.setCodStatus(Boolean.valueOf("ATIVO"));
        usuarioRepository.save(cliente);
        return ResponseEntity.ok(Map.of("message", "Cliente cadastrado com sucesso!"));
    }

    @PostMapping("/cadastro/confeiteiro")
    public ResponseEntity<?> cadastrarConfeiteiro(@RequestBody Confeiteiro confeiteiro) {
        confeiteiro.setTipoUsuario(TipoUsuario.CONFEITEIRO);
        confeiteiro.setCodStatus(Boolean.valueOf("ATIVO"));
        // Se o campo promocao vier nulo do front, definimos como false
        if (confeiteiro.getPromocao() == null) confeiteiro.setPromocao(false);

        usuarioRepository.save(confeiteiro);
        return ResponseEntity.ok(Map.of("message", "Confeiteiro cadastrado com sucesso!"));
    }

    @PostMapping("/cadastro/entregador")
    public ResponseEntity<?> cadastrarEntregador(@RequestBody Entregador entregador) {
        entregador.setTipoUsuario(TipoUsuario.ENTREGADOR);
        entregador.setCodStatus(Boolean.valueOf("ATIVO"));
        usuarioRepository.save(entregador);
        return ResponseEntity.ok(Map.of("message", "Entregador cadastrado com sucesso!"));
    }

    // --- LOGIN UNIFICADO (Diferenciando o tipo no retorno) ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String senha = loginData.get("senha");

        // 1. Buscamos o usuário primeiro
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // 2. Verificamos se ele existe e se a senha bate
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            Usuario user = usuarioOpt.get();
            String tipo = user.getClass().getSimpleName().toUpperCase();

            // Retorno de Sucesso (Map)
            Map<String, Object> respostaSucesso = Map.of(
                    "token", "token-fake-jwt-" + user.getId(),
                    "user", Map.of(
                            "id", user.getId(),
                            "nome", user.getNome(),
                            "email", user.getEmail(),
                            "tipo", tipo
                    )
            );
            return ResponseEntity.ok(respostaSucesso);
        }

        // 3. Se algo falhar, retorno de Erro (String)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha incorretos.");
    }
}