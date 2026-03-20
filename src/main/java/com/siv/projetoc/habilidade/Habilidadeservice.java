package com.siv.projetoc.habilidade;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Habilidadeservice {

    private final HabilidadeRepository repository;

    public Habilidadeservice(HabilidadeRepository repository) {
        this.repository = repository;
    }

    public List<Habilidade> findAll() {
        return repository.findAll();
    }
}
