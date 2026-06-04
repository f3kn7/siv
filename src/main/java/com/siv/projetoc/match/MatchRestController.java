package com.siv.projetoc.match;

import com.siv.projetoc.match.dto.VoluntarioMatchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/matches")
public class MatchRestController {

    private final MatchService matchService;

    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;

    }

    @PutMapping("/{matchId}/confirmar")
    public ResponseEntity<VoluntarioMatchDTO> confirmar(@PathVariable Long matchId) {
        Match match = matchService.confirmar(matchId);
        return ResponseEntity.ok(VoluntarioMatchDTO.factoryFrom(match));
    }

    @PutMapping("/{matchId}/recusar")
    public ResponseEntity<VoluntarioMatchDTO> recusar(@PathVariable Long matchId) {
        Match match = matchService.recusar(matchId);
        return ResponseEntity.ok(VoluntarioMatchDTO.factoryFrom(match));
    }

    @GetMapping("/voluntario/{voluntarioId}/pendentes")
    public ResponseEntity<List<VoluntarioMatchDTO>> listaPendentes(@PathVariable Long voluntarioId) {
        List<Match> matches = matchService.listarPendentesDoVoluntario(voluntarioId);
        List<VoluntarioMatchDTO> dtos = new ArrayList<>();
        for (Match match : matches) {
            VoluntarioMatchDTO dto = VoluntarioMatchDTO.factoryFrom(match);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/gerar/{tarefaId}")
    public String gerar(@PathVariable("tarefaId") Long tarefaId) {
        matchService.gerarMatchsPorTarefa(tarefaId);
        return "Matches gerados com sucesso, id_tarefa: " + tarefaId;
    }

    @GetMapping("/listar/requisicao/{requisicaoId}")
    public List<Match> listar(@PathVariable("requisicaoId") Long requisicaoId) {

        return matchService.listarPorRequisicaoHabilidade(requisicaoId);
    }

}


