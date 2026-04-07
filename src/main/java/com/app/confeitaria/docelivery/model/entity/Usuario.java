package com.app.confeitaria.docelivery.model.entity;

import com.app.confeitaria.docelivery.model.enums.TipoUsuario;
import com.app.confeitaria.docelivery.model.repository.UsuarioRepository;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // informa que o padrão utilizado é uma tabela para todos os usuários
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // informa que é o nome da coluna que vai ser utilizada
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor  //construtor padrão
@AllArgsConstructor //Construtor com todos os atributos
@Builder            //Forma diferenciada para criar objetos

public  class Usuario implements UserDetails {

    @Id       //  PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //  Auto-Incremento (identificado sequencialmente de 1 em 1)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = true, length = 100)
    private String nome;
    @Column(nullable = true, length = 20)
    private String cpf;
    @Column(nullable = true, length = 10)
    private String cep;
    @Column(nullable = true, length = 100)
    private String endereco;
    @Column(nullable = true, length = 50)
    private String bairro;
    @Column(nullable = true, length = 20)
    private String cidade;
    @Column(nullable = true, length = 2)
    private String uf;
    @Column(nullable = true, length = 20)
    private String telefone;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = false, length = 250)
    private String senha;
    @Column(nullable = true)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING) // ADICIONE ISSO
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private TipoUsuario tipoUsuario;

    @Column(columnDefinition = "TINYINT")
    private Boolean codStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Se o tipoUsuario for nulo, retorna uma lista vazia em vez de estourar erro
        if (this.tipoUsuario == null) {
            return Collections.emptyList();
        }

        // Converte o Enum para o formato que o Spring Security entende (ROLE_CLIENTE, etc)
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.tipoUsuario.name()));
    }
    @Override
    public String getPassword() {
        return this.senha; // Mude para isso
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
