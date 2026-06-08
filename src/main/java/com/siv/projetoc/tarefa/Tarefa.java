package com.siv.projetoc.tarefa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siv.projetoc.common.BaseEntity;
import com.siv.projetoc.enums.DiaSemana;
import com.siv.projetoc.enums.StatusTarefa;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidade;
import com.siv.projetoc.usuario.Instituicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("LanguageDetectionInspection")
@Entity
@Getter
@Setter
@Table(name = "tarefa")
@AttributeOverride(name = "id", column = @Column(name = "tarefa_id"))
public class Tarefa extends BaseEntity {

    @Column(nullable = false,  length = 100)
    private String nome;

    @Size(max = 1000)
    @Column(nullable = false, columnDefinition = "TEXT") // definição para textos longos no banco de dados
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 13)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false, length = 150)
    private String rua;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private StatusTarefa status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)   // Muitas tarefas para uma instituição - FK obrigatória
    @JoinColumn(name = "fk_instituicao", nullable = false) // cria a coluna FK na tabela tarefa apontando para instituicao
    private Instituicao instituicao;                       // [Tarefa] -> entidade dependente de [Instituição] - não existe tarefa sem instituição (fk: nullable = false)

    // @OneToMany(mappedBy = "tarefa"): bidirecional - permite navegar de Tarefa para suas requisições diretamente (tarefa.getRequisicoes())   Tarefa (List) 1 ────────── 0..* RequisicaoHabilidade
    // só quando faz sentido navegar pelos dois lados - quando usa cascade — precisa do @OneToMany para funcionar - não precisa ir ao banco outra vez;
    @JsonIgnore
    @OneToMany(mappedBy = "tarefa", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true) // Tarefa tem MUITAS RequisicaoHabilidade → List
    private List<RequisicaoHabilidade> requisicoes = new ArrayList<>();

}


