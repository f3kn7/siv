package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.enums.DiaSemana;
import com.siv.projetoc.habilidade.Habilidade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalTime;
import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    //Derived Query (findByVoluntarioId) pode dar problema -> @Query porque o id está na classe pai Usuario — Spring Data não resolve herança pelo nome do metodo
    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :id")
    List<Disponibilidade> findByVoluntario(Long id);

    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :id AND d.diaSemana = :diaSemana")
    List<Disponibilidade> findByVoluntarioIdAndDiaSemana(Long id, DiaSemana diaSemana);


    @Query("SELECT DISTINCT d FROM Disponibilidade d " +
            "JOIN d.voluntario v " +
            "JOIN v.habilidades h " +
            "WHERE v.cidade = :cidade " +
            "AND h = :habilidade " +
            "AND d.diaSemana = :diaSemana " +
            "AND d.horaInicio <= :horaInicio " +
            "AND d.horaFim >= :horaFim " +
            "AND NOT EXISTS (SELECT 1 FROM Match m  " +
            "WHERE m.disponibilidade = d " +
            "AND m.status IN ('PENDENTE', 'CONFIRMADO')) ")
    List<Disponibilidade> findDisponibilidadeParaMatch(
            @Param("cidade") String cidade,
            @Param("habilidade") Habilidade habilidade,
            @Param("diaSemana") DiaSemana diaSemana,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFim") LocalTime horaFim,
            Pageable pageable);
}
