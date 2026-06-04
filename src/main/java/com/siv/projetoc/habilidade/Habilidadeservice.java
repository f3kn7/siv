package com.siv.projetoc.habilidade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class Habilidadeservice {

    private final HabilidadeRepository repository;

    public Habilidadeservice(HabilidadeRepository repository) {
        this.repository = repository;
    }

    public List<Habilidade> findAll() {
        return repository.findAll();
    }
}
