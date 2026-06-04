package com.siv.projetoc.match;


import com.siv.projetoc.enums.StatusMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Match m WHERE m.disponibilidade.id = :nro_disp AND m.requisicaoHabilidade.id = :requisicao_habilidade_id")
    boolean existsByDisponibilidadeIdAndRequisicaoId(@Param("nro_disp") Long nro_disp, @Param("requisicao_habilidade_id") Long requisicao_habilidade_id);

    @Query("SELECT m FROM Match m WHERE m.requisicaoHabilidade.id = :requisicao_habilidade_id AND m.status = :status")
    List<Match> findByRequisicaoHabilidadeIdAndStatus(@Param("requisicao_habilidade_id") Long requisicao_habilidade_id, @Param("status") StatusMatch status);

    @Query("SELECT m FROM Match m WHERE m.requisicaoHabilidade.id = :requisicao_habilidade_id")
    List<Match> findByRequisicaoHabilidadeId(@Param("requisicao_habilidade_id") Long requisicao_habilidade_id);

    @Query("SELECT m FROM Match m WHERE m.disponibilidade.voluntario.id = :voluntarioId AND m.status = 'PENDENTE'")
    List<Match> findPendentesByVoluntario(@Param("voluntarioId") Long voluntarioId);

    @Query("SELECT COUNT(m) FROM Match m WHERE m.requisicaoHabilidade.id = :reqId AND m.status = :status")
    long countByRequisicaoHabilidadeAndStatus(@Param("reqId") Long requisicaoId, @Param("status") StatusMatch status);

    @Modifying
    @Query("UPDATE Match m SET m.status = 'EXPIRADO_JA_PREENCHIDO' WHERE m.requisicaoHabilidade.id = :reqId AND m.status = 'PENDENTE'")
    void expirarPendentesPorRequisicao(@Param("reqId") Long reqId);
}
