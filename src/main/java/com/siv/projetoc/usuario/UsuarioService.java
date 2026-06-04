package com.siv.projetoc.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final InstituicaoRepository instituicaoRepository;

    public UsuarioService(VoluntarioRepository voluntarioRepository, InstituicaoRepository instituicaoRepository) {
        this.voluntarioRepository = voluntarioRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    //CRUD #Voluntario ---------------------------------------------------
    @Transactional
    public Voluntario salvarVoluntario(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public List<Voluntario> listarVoluntarios() {
        return voluntarioRepository.findAll();
    }

    public Voluntario buscarVoluntarioPorId(Long id) {
        return voluntarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Voluntário não encontrado"));
    }

    @Transactional
    public void deletarVoluntario(Long id) {
        voluntarioRepository.deleteById(id);
    }

    //CRUD #Instituição -------------------------------------------------
    @Transactional
    public Instituicao salvarInstituicoe(Instituicao instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    public List<Instituicao> listarInstituicoes() {
        return instituicaoRepository.findAll();
    }

    public Instituicao buscarInstituicoPorId(Long id) {

        return instituicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
    }

    @Transactional
    public void deletarInstituicao(Long id) {
        instituicaoRepository.deleteById(id);
    }


}
