package com.siv.projetoc.usuario;

import com.siv.projetoc.disponibilidade.Disponibilidade;
import com.siv.projetoc.habilidade.Habilidade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("VOL") // quando tipo = "VOL" na tabela usuario, é um voluntario - comportamento em runtime (como o JPA lê e escreve os dados)
@Table(name = "voluntario")
@PrimaryKeyJoinColumn(name = "fk_usuario") // nomeia manualmente a PK/FK da herança, não deixa o jpa criar automaticamente um*****
public class Voluntario extends Usuario {

    private String cpf;

    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disponibilidade> disponibilidadeList;

    @ManyToMany
    @JoinTable(
            name = "voluntario_habilidade", // nome da tabela intermediária (relação N,N -> tabela própria)
            joinColumns = @JoinColumn(name = "fk_voluntario"), // FK para voluntario
            inverseJoinColumns = @JoinColumn(name = "fk_habilidade") // FK para habilidade
    )
    private List<Habilidade> habilidades = new ArrayList<>();
}
