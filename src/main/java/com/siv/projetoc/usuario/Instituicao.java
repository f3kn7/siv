package com.siv.projetoc.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("INST")
@Table(name = "instituicao")
public class Instituicao extends Usuario{

    private String cnpj;
    private String estatutoSocial;
}
