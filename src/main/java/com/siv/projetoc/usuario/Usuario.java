package com.siv.projetoc.usuario;

import com.siv.projetoc.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // diz ao JPA que essa classe é uma tabela no banco
@Getter // lombok gera todos os getters automaticamente
@Setter // lombok gera todos os setters automaticamente
@Inheritance(strategy = InheritanceType.JOINED) // herança JOINED - cada classe tem sua tabela, filhas referenciam a pai via FK na PK (usuario, voluntario, instituicao)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING) // coluna que identifica o tipo do usuario na tabela pai
@Table(name = "usuario") // nome da tabela no banco
@AttributeOverride(name = "id", column = @Column(name = "usuario_id"))
public abstract class Usuario extends BaseEntity {

    private String login;
    private String senha;
    private String nome;
    private String email;
    private String telefone;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

}
