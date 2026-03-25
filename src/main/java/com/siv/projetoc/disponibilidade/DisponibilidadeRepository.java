package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.usuario.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    // @Query porque o id está na classe pai Usuario — Spring Data não resolve herança pelo nome do método
    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :voluntarioId")
    List<Disponibilidade> findByVoluntario(Long voluntarioId);

    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :voluntarioId AND d.diaSemana = :diaSemana")
    List<Disponibilidade> findByVoluntarioIdAndDiaSemana(Long voluntarioId, DiaSemana diaSemana);
}
