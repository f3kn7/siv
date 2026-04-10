package com.siv.projetoc.requisicaohabilidade;

import com.siv.projetoc.usuario.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisicaoHabilidadeRepository extends JpaRepository<RequisicaoHabilidade, Long> {

    @Query("SELECT r FROM RequisicaoHabilidade r WHERE r.tarefa.id = :id")
    List<RequisicaoHabilidade> findByTarefaId(@Param("tarefaId") Long id);

}
