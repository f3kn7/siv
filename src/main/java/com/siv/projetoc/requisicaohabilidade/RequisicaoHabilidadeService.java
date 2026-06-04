package com.siv.projetoc.requisicaohabilidade;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RequisicaoHabilidadeService {

    private final RequisicaoHabilidadeRepository requisicaoHabilidadeRepository;

    public RequisicaoHabilidadeService(RequisicaoHabilidadeRepository requisicaoHabilidadeRepository) {
        this.requisicaoHabilidadeRepository = requisicaoHabilidadeRepository;
    }

    @Transactional
    public RequisicaoHabilidade salvar(RequisicaoHabilidade requisicaoHabilidade) {
        return requisicaoHabilidadeRepository.save(requisicaoHabilidade);
    }

    public List<RequisicaoHabilidade> listarPorTarefa(Long id) {
        return requisicaoHabilidadeRepository.findByTarefaId(id);
    }

    public RequisicaoHabilidade buscarPorId(Long id) {
        return requisicaoHabilidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Requisicao nao encontrada"));
    }
}
