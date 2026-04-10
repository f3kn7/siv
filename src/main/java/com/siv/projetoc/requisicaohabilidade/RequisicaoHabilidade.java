package com.siv.projetoc.requisicaohabilidade;

import com.siv.projetoc.common.BaseEntity;
import com.siv.projetoc.habilidade.Habilidade;
import com.siv.projetoc.tarefa.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter         // @UniqueConstraint: garante que a mesma habilidade não se repita na mesma tarefa
@Table(name = "requisicao_habilidade", uniqueConstraints = @UniqueConstraint(name = "uk_tarefa_habilidade", columnNames = {"fk_tarefa", "fk_habilidade"}))
@AttributeOverride(name = "id", column = @Column(name = "requisicao_habilidade_id"))
public class RequisicaoHabilidade extends BaseEntity {

    @Column(name ="qtd_habilidade", nullable = false)
    private Integer quantidadeHabilidade;

    @ManyToOne(optional = false) //Muitas requisições para uma habilidade (varias requisiçoes, mas não do mesmo tipo)
    @JoinColumn(name = "fk_habilidade", nullable = false)
    private Habilidade habilidade; //entidade fraca - não existe sem habilidade

    @ManyToOne(optional = false) //Muitas requisições para uma tarefa (varias requisiçoes, mas não do mesmo tipo)
    @JoinColumn(name = "fk_tarefa", nullable = false)
    private Tarefa tarefa; //entidade fraca - não existe sem tarefa

}
