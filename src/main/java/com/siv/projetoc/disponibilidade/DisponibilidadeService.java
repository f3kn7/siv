package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.enums.DiaSemana;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository repository;

    public DisponibilidadeService(DisponibilidadeRepository repository) {
        this.repository = repository;
    }

    public List<Disponibilidade> listarPorVoluntario(Long id) {
        return repository.findByVoluntario(id);
    }

    public List<Disponibilidade> listarPorVoluntarioEdia(Long id, DiaSemana diaSemana) {
        return repository.findByVoluntarioIdAndDiaSemana(id, diaSemana);
    }

    public Disponibilidade salvar(Disponibilidade disponibilidade) {
        return repository.save(disponibilidade);
    }
}
