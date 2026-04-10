package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    //Derived Query (findByVoluntarioId) pode dar problema -> @Query porque o id está na classe pai Usuario — Spring Data não resolve herança pelo nome do metodo
    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :id")
    List<Disponibilidade> findByVoluntario(Long id);

    @Query("SELECT d FROM Disponibilidade d WHERE d.voluntario.id = :id AND d.diaSemana = :diaSemana")
    List<Disponibilidade> findByVoluntarioIdAndDiaSemana(Long id, DiaSemana diaSemana);
}
