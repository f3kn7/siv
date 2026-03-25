package com.siv.projetoc.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("INST")
@Table(name = "instituicao")
@PrimaryKeyJoinColumn(name = "fk_usuario")
public class Instituicao extends Usuario{

    private String cnpj;
    private String estatutoSocial;
}
