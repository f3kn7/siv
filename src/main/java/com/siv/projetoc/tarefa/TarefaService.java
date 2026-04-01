package com.siv.projetoc.tarefa;

import com.siv.projetoc.enums.StatusTarefa;
import com.siv.projetoc.usuario.Instituicao;
import com.siv.projetoc.usuario.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final InstituicaoRepository instituicaoRepository;

    public TarefaService(TarefaRepository tarefaRepository, InstituicaoRepository instituicaoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    //salva nova tarefa (sempre começa com status: ABERTA)
    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setStatus(StatusTarefa.ABERTA);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarPorInstituicao(Long InstituicaoId) {
        return tarefaRepository.findByInstituicaoId(InstituicaoId);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public List<Tarefa> buscarAbertasPorCidade(String cidade) {

        return tarefaRepository.findAbertasPorCidade(cidade, StatusTarefa.ABERTA);
    }

    public void AtualizarStatus(Long id, StatusTarefa novoStatus) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setStatus(novoStatus);
        tarefaRepository.save(tarefa);
    }
}
