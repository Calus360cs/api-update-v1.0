package com.app.confeitaria.docelivery.service;

import com.app.confeitaria.docelivery.model.entity.Confeiteiro;
import com.app.confeitaria.docelivery.model.entity.Usuario;
import com.app.confeitaria.docelivery.model.repository.ConfeiteiroRepository;
import com.app.confeitaria.docelivery.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConfeiteiroRepository confeiteiroRepository;

    // 1. AÇÃO GLOBAL: Login (Funciona para qualquer subclasse)
    public Optional<Usuario> realizarLogin(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(user -> user.getSenha().equals(senha)); // Em produção, use BCrypt!
    }

    // 2. AÇÃO ESPECÍFICA: Salvar Confeiteiro
    @Transactional
    public Confeiteiro salvarConfeiteiro(Confeiteiro confeiteiro) {
        // Validação que só existe na mãe, mas verificada antes de salvar o filho
        if (usuarioRepository.existsByCpf(confeiteiro.getCpf())) {
            throw new RuntimeException("CPF já cadastrado no sistema!");
        }
        return confeiteiroRepository.save(confeiteiro);
    }

    // 3. AÇÃO DE LISTAGEM: Apenas Confeiteiros por Cidade
    public List<Confeiteiro> listarLojasPorCidade(String cidade) {
        return confeiteiroRepository.findByCidadeIgnoreCase(cidade);
    }
}

