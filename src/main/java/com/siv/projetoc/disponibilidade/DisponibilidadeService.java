package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.usuario.Voluntario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository repository;

    public DisponibilidadeService(DisponibilidadeRepository repository) {
        this.repository = repository;
    }

    public List<Disponibilidade> listarPorVoluntario(Long voluntarioId) {
        return repository.findByVoluntario(voluntarioId);
    }

    public List<Disponibilidade> listarPorVoluntarioEdia(Long voluntarioId, DiaSemana diaSemana) {
        return repository.findByVoluntarioIdAndDiaSemana(voluntarioId, diaSemana);
    }

    public Disponibilidade salvar(Disponibilidade disponibilidade) {
        return repository.save(disponibilidade);
    }
}
