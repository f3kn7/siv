package com.siv.projetoc.match;

import com.siv.projetoc.common.BaseEntity;
import com.siv.projetoc.disponibilidade.Disponibilidade;
import com.siv.projetoc.enums.StatusMatch;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "match_requisicao")
@AttributeOverride(name = "id", column = @Column(name = "match_requisicao_id"))
public class Match extends BaseEntity {

    @Column(nullable = false)
    private Instant dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatch status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_disponibilidade", nullable = false)
    private Disponibilidade disponibilidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_requisicao_habilidade", nullable = false)
    private RequisicaoHabilidade requisicaoHabilidade;


}
