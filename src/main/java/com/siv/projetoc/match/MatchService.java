package com.siv.projetoc.match;

import com.siv.projetoc.disponibilidade.Disponibilidade;
import com.siv.projetoc.disponibilidade.DisponibilidadeRepository;
import com.siv.projetoc.disponibilidade.DisponibilidadeService;
import com.siv.projetoc.enums.StatusMatch;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidade;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidadeService;
import com.siv.projetoc.tarefa.Tarefa;
import com.siv.projetoc.tarefa.TarefaService;
import com.siv.projetoc.usuario.Voluntario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    /*                          Critérios p/ o matching:
                                1: voluntario.cidade == tarefa.cidade
                                2: voluntario.habilidades contem tarefa.requisicaoHabilidade.habilidade
                                3: voluntario.disponibilidade cobre tarefa.diaSemana + horario
                                4: disponibilidade não está ocupada em outro match (PENDENTE/CONFIRMADO) */

    private static final int FATOR_LIMITE = 2; //envia pra mais voluntários que o necessário — previsão simples de recusas e pendentes

    private final MatchRepository matchRepository;
    private final TarefaService tarefaService;
    private final RequisicaoHabilidadeService requisicaoHabilidadeService;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final DisponibilidadeService disponibilidadeService;

    public MatchService(MatchRepository matchRepository, TarefaService tarefaService, RequisicaoHabilidadeService requisicaoHabilidadeService,
                        DisponibilidadeRepository disponibilidadeRepository, DisponibilidadeService disponibilidadeService) {
        this.matchRepository = matchRepository;
        this.tarefaService = tarefaService;
        this.requisicaoHabilidadeService = requisicaoHabilidadeService;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.disponibilidadeService = disponibilidadeService;
    }

    public void gerarMatchsPorTarefa(Long id) {

        Tarefa tarefa = tarefaService.buscarPorId(id);
        List<RequisicaoHabilidade> requisicoes = requisicaoHabilidadeService.listarPorTarefa(id);

        List<Match> matchesParaSalvar = new ArrayList<>(); // acumulando matches para salvar, evitan muitos inserts individuais no banco.

        for (RequisicaoHabilidade requisicaoHabilidade : requisicoes) {

            int limiteMatches = requisicaoHabilidade.getQuantidadeHabilidade() * FATOR_LIMITE;

            Pageable limite = PageRequest.ofSize(limiteMatches);

            List<Disponibilidade> candidatas = disponibilidadeRepository.findDisponibilidadeParaMatch(
                    tarefa.getCidade(),
                    requisicaoHabilidade.getHabilidade(),
                    tarefa.getDiaSemana(),
                    tarefa.getHoraInicio(),
                    tarefa.getHoraFim(),
                    limite);

            for (Disponibilidade disp : candidatas) {

                boolean jaExiste = matchRepository.existsByDisponibilidadeIdAndRequisicaoId(disp.getId(), requisicaoHabilidade.getId());

                if (!jaExiste) {

                    Voluntario v = disp.getVoluntario();
                    System.out.printf("Criando match pra: %s id: %s%n", v.getNome(), v.getId());

                    Match match = new Match();
                    match.setDisponibilidade(disp);
                    match.setRequisicaoHabilidade(requisicaoHabilidade);
                    match.setStatus(StatusMatch.PENDENTE);
                    match.setDataCriacao(Instant.now());
                    matchesParaSalvar.add(match);
                }
            }
        }
        //salva todos no banco em um única operação
        if (!matchesParaSalvar.isEmpty()) {
            matchRepository.saveAll(matchesParaSalvar);
        }
    }

    public Match confirmar(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match não encontrado!"));
        match.setStatus(StatusMatch.CONFIRMADO);
        return matchRepository.save(match);
    }

    public Match recusar(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match não encontrado!"));
        match.setStatus(StatusMatch.RECUSADO);
        return matchRepository.save(match);
    }

    public List<Match> listarConfirmadosPorRequisicaoHabilidade(Long requisicaoHabilidadeId) {
        return matchRepository.findByRequisicaoHabilidadeIdAndStatus(requisicaoHabilidadeId, StatusMatch.CONFIRMADO);
    }

    public List<Match> listarPorRequisicaoHabilidade(Long requisicaoHabilidadeId) {
        return matchRepository.findByRequisicaoHabilidadeId(requisicaoHabilidadeId);
    }

}

