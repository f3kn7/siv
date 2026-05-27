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

    @Column(nullable = true, unique = true, length = 50)
    private String nome;


}
