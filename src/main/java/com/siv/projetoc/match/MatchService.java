package com.siv.projetoc.match;

import com.siv.projetoc.disponibilidade.DisponibilidadeService;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidadeService;
import com.siv.projetoc.tarefa.TarefaService;
import com.siv.projetoc.usuario.VoluntarioRepository;

public class MatchService {

    private final TarefaService tarefaService;
    private final RequisicaoHabilidadeService requisicaoHabilidadeService;
    private final VoluntarioRepository voluntarioRepository;
    private final DisponibilidadeService disponibilidadeService;


    public MatchService(TarefaService tarefaService, RequisicaoHabilidadeService requisicaoHabilidadeService, VoluntarioRepository voluntarioRepository, DisponibilidadeService disponibilidadeService) {
        this.tarefaService = tarefaService;
        this.requisicaoHabilidadeService = requisicaoHabilidadeService;
        this.voluntarioRepository = voluntarioRepository;
        this.disponibilidadeService = disponibilidadeService;
    }



}
