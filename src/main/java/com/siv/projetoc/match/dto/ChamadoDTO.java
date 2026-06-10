package com.siv.projetoc.match.dto;

import com.siv.projetoc.match.Match;

public record ChamadoDTO(
        Long matchId,
        String habilidade,
        String voluntario,
        String telefone,
        String status
) {
    public static ChamadoDTO factoryFrom(Match match) {
        return new ChamadoDTO(
                match.getId(),
                match.getRequisicaoHabilidade().getHabilidade().getNome(),
                match.getDisponibilidade().getVoluntario().getNome(),
                match.getDisponibilidade().getVoluntario().getTelefone(),
                match.getStatus().name()
        );
    }
}
