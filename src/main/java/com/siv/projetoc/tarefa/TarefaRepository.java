package com.siv.projetoc.tarefa;

import com.siv.projetoc.enums.StatusTarefa;
import com.siv.projetoc.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // @Query obrigatório - id está na classe pai Usuario, Spring Data não resolve herança pelo nome do metodo
    @Query("SELECT t FROM Tarefa t WHERE t.instituicao.id = :instituicaoId")
    List<Tarefa> findByInstituicaoId(@Param("instituicaoId") Long instituicaoId);

    @Query("SELECT t FROM Tarefa t WHERE t.cidade = :cidade AND t.status = :status")
    List<Tarefa> findAbertasPorCidade(@Param("cidade") String cidade, @Param("status")StatusTarefa status);

}
