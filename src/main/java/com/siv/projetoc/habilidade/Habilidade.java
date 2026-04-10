package com.siv.projetoc.habilidade;

import com.siv.projetoc.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "habilidade_id"))
public class Habilidade extends BaseEntity {

    private String nome;


}
