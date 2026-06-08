package com.siv.projetoc.match.dto;

import com.siv.projetoc.enums.StatusMatch;
import com.siv.projetoc.habilidade.Habilidade;
import com.siv.projetoc.match.Match;
import com.siv.projetoc.usuario.Voluntario;

import java.util.List;
import java.util.stream.Collectors;

public record VoluntarioMatchDTO(
        Long matchId,
        String nomeVoluntario,
        String email,
        String telefone,
        List<String> habilidades,
        StatusMatch status
) {

    public static VoluntarioMatchDTO factoryFrom(Match match) {
        Voluntario voluntario = match.getDisponibilidade().getVoluntario();

        List<String> nomesHabilidades = voluntario.getHabilidades()
                .stream()
                .map(Habilidade::getNome)
                .collect(Collectors.toList());


        return new VoluntarioMatchDTO(
                match.getId(),
                voluntario.getNome(),
                voluntario.getEmail(),
                voluntario.getTelefone(),
                nomesHabilidades,
                match.getStatus()
        );
    }
}
