package com.siv.projetoc.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("VOL") // quando tipo = "VOL" na tabela usuario, é um voluntario
@Table(name = "voluntario")
public class Voluntario extends Usuario {

    private String cpf;

}
