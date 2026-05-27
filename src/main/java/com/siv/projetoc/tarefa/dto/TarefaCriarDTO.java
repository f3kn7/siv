package com.siv.projetoc.tarefa.dto;

import com.siv.projetoc.enums.DiaSemana;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TarefaCriarDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    private String titulo;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @FutureOrPresent(message = "A data deve ser hoje ou no futuro")
    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Dia da semana é obrigatório")
    private DiaSemana diaSemana;

    @NotNull(message = "Horário de início é obrigatório")
    private LocalTime horarioInicio;

    @NotNull(message = "Horário de fim é obrigatório")
    private LocalTime horarioFim;

    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 150)
    private String rua;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2)
    private String estado;

    @NotEmpty(message = "Pelo menos uma habilidade é obrigatória")
    @Valid
    private List<RequisicaoHabDTO> requisicaoHab = new ArrayList<RequisicaoHabDTO>();
}
