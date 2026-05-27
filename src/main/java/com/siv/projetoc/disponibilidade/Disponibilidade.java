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
@Table(name = "disponibilidade", indexes = {@Index(name = "idx_disp_dia_horarios", columnList = "dia_semana, hora_inicio, hora_fim")})
//Index composto, REGRA DE OURO ->> igualdade (=) antes de range (<, >, <=, >=)
@AttributeOverride(name = "id", column = @Column(name = "nro_disp"))
public class Disponibilidade extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 13)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //muitas disponibilidades para um voluntario — FK obrigatória
    @JoinColumn(name = "fk_voluntario", nullable = false)
    private Voluntario voluntario;

    //verifica se a disponibilidade cobre horario da tarefa
    public boolean isCompativel(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim) {

        return this.diaSemana == diaSemana
                && !this.horaInicio.isAfter(horaInicio)  //!isAfter = começa antes ou igual
                && !this.horaFim.isBefore(horaFim);      //!isBefore = termina depois ou igual

    }

}
