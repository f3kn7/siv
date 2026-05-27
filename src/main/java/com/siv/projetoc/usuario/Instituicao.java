package com.siv.projetoc.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("INST")
@Table(name = "instituicao")
@PrimaryKeyJoinColumn(name = "fk_usuario")
public class Instituicao extends Usuario{

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Size(max = 500, message = "Estatuto deve ter no máximo 500 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String estatutoSocial;
}
