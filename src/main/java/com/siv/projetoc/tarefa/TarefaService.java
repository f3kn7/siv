package com.siv.projetoc.tarefa;

import com.siv.projetoc.enums.DiaSemana;
import com.siv.projetoc.enums.StatusTarefa;
import com.siv.projetoc.habilidade.Habilidade;
import com.siv.projetoc.habilidade.HabilidadeRepository;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidade;
import com.siv.projetoc.requisicaohabilidade.RequisicaoHabilidadeRepository;
import com.siv.projetoc.tarefa.dto.RequisicaoHabDTO;
import com.siv.projetoc.tarefa.dto.TarefaCriarDTO;
import com.siv.projetoc.usuario.Instituicao;
import com.siv.projetoc.usuario.InstituicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final RequisicaoHabilidadeRepository requisicaoHabilidadeRepository;

    public TarefaService(TarefaRepository tarefaRepository, InstituicaoRepository instituicaoRepository, HabilidadeRepository habilidadeRepository, RequisicaoHabilidadeRepository requisicaoHabilidadeRepository) {
        this.tarefaRepository = tarefaRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.habilidadeRepository = habilidadeRepository;
        this.requisicaoHabilidadeRepository = requisicaoHabilidadeRepository;
    }

    public List<Tarefa> listarPorInstituicao(Long id) {
        return tarefaRepository.findByInstituicaoId(id);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public List<Tarefa> buscarAbertasPorCidade(String cidade) {

        return tarefaRepository.findAbertasPorCidade(cidade, StatusTarefa.ABERTA);
    }

    @Transactional
    public Tarefa salvarComRequisicaoHabilidade(TarefaCriarDTO dto, Long instituicaoId) {

        Instituicao instituicao = instituicaoRepository.findById(instituicaoId).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

        Tarefa tarefa = new Tarefa();

        tarefa.setNome(dto.getNome());
        tarefa.setDescricao(dto.getDescricao());
        DiaSemana diaSemana = DiaSemana.fromDayOfWeek(dto.getData().getDayOfWeek()); // Derivação do dia da semana a partir da data (LocalDate -> DayOfWeek -> DiaSemana)
        tarefa.setDiaSemana(diaSemana);
        tarefa.setHoraInicio(dto.getHorarioInicio());
        tarefa.setHoraFim(dto.getHorarioFim());

        tarefa.setRua(dto.getRua());
        tarefa.setBairro(dto.getBairro());
        tarefa.setCidade(dto.getCidade());
        tarefa.setEstado(dto.getEstado());

        tarefa.setStatus(StatusTarefa.ABERTA);
        tarefa.setInstituicao(instituicao);

        Tarefa tarefaSalva = tarefaRepository.save(tarefa); // <- precisa salvar primeiro para ter ID da tarefa - RequisiçãoHabilidade tem FK obrigatoria da tarefa

        for (RequisicaoHabDTO requisicaoHabDTO : dto.getRequisicaoHab()) {
            Habilidade habilidade = habilidadeRepository.findById(requisicaoHabDTO.getHabilidadeId())
                    .orElseThrow(() -> new RuntimeException("Habilidade não encontrada: " + requisicaoHabDTO.getHabilidadeId()));

            RequisicaoHabilidade requisicaoHabilidade = new RequisicaoHabilidade();
            requisicaoHabilidade.setTarefa(tarefaSalva);
            requisicaoHabilidade.setHabilidade(habilidade);
            requisicaoHabilidade.setQuantidadeHabilidade(requisicaoHabDTO.getQuantidade());

            requisicaoHabilidadeRepository.save(requisicaoHabilidade);
        }

        return tarefaSalva;
    }


}
