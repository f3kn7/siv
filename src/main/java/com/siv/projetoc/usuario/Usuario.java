package com.siv.projetoc.usuario;

import com.siv.projetoc.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity // diz ao JPA que essa classe é uma tabela no banco
@Getter // lombok gera todos os getters automaticamente
@Setter // lombok gera todos os setters automaticamente
@Inheritance(strategy = InheritanceType.JOINED)
// herança JOINED - cada classe tem sua tabela, filhas referenciam a pai via FK na PK (usuario, voluntario, instituicao)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
// coluna que identifica o tipo do usuario na tabela pai
@Table(name = "usuario", indexes = {@Index(name = "idx_usuario_cidade", columnList = "cidade")})
// nome da tabela no banco e index na coluna cidade para o indice da Btree ->> busca O(log n) muito melhor que table scan ->> O(n)
@AttributeOverride(name = "id", column = @Column(name = "usuario_id"))
public abstract class Usuario extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Email é obrigatorio!")
    //Bean Validation: valida na aplicação antes de chegar no banco @Valid no controller ativa essas anotações
    @Email(message = "Email inválido")
    @Size(max = 254)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 14)
    private String telefone;

    @Column(nullable = false, length = 150)
    private String rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;


}
