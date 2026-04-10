package com.siv.projetoc.disponibilidade;

import com.siv.projetoc.common.BaseEntity;
import com.siv.projetoc.enums.DiaSemana;
import com.siv.projetoc.usuario.Voluntario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "disponibilidade")
@AttributeOverride(name = "id", column = @Column(name = "nro_disp"))
public class Disponibilidade extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    private LocalTime horaFim;

    @ManyToOne(optional = false) //muitas disponibilidades para um voluntario — FK obrigatória
    @JoinColumn(name ="fk_voluntario", nullable = false)
    private Voluntario voluntario;

}
