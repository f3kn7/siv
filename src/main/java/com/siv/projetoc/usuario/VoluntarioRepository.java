package com.siv.projetoc.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

    @Query("SELECT DISTINCT v FROM Voluntario v JOIN v.habilidades h WHERE v.cidade = :cidade AND h = :habilidade")
    List<Voluntario> findByCidadeAndHabilidade(
            @Param("cidade") String cidade,
            @Param("habilidade") String habilidade);
}
