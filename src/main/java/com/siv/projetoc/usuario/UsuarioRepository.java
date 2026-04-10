package com.siv.projetoc.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Derived query — Spring Data JPA gera o SQL a partir do nome do metodo
    Usuario findByEmail(String email);
}

/* UsuarioRepository herda as seguintes operações prontas do JpaRepository:

repository.findAll()        -> lista todos
repository.findById(1L)     -> busca por ID
repository.save(usuario)    -> salva (insert ou update)
repository.deleteById(1L)   -> deleta por ID
repository.count()          -> conta quantos registros tem
repository.existsById(1L)   -> verifica se existe*/