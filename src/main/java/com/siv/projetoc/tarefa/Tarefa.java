package com.siv.projetoc.tarefa;

import com.siv.projetoc.common.BaseEntity;
import com.siv.projetoc.enums.DiaSemana;
import com.siv.projetoc.enums.StatusTarefa;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidade;
import com.siv.projetoc.usuario.Instituicao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tarefa")
@AttributeOverride(name = "id", column = @Column(name = "tarefa_id"))
public class Tarefa extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status;

    @ManyToOne(optional = false)      // Muitas tarefas para uma instituição - FK obrigatória
    @JoinColumn(name = "fk_instituicao", nullable = false) // cria a coluna FK na tabela tarefa apontando para instituicao
    private Instituicao instituicao;          // entidade fraca->[tarefa] - não existe tarefa sem instituição

    // @OneToMany(mappedBy = "tarefa"): bidirecional - permite navegar de Tarefa para suas requisições diretamente (tarefa.getRequisicoes())   Tarefa (List) 1 ────────── 0..* RequisicaoHabilidade
    // só quando faz sentido navegar pelos dois lados - quando usa cascade — precisa do @OneToMany para funcionar - não precisa ir ao banco outra vez;
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true) // Tarefa tem MUITAS RequisicaoHabilidade → List
    private List<RequisicaoHabilidade> requisicoes = new ArrayList<>();
}


