package com.siv.projetoc;

import com.siv.projetoc.tarefa.TarefaService;
import com.siv.projetoc.tarefa.dto.RequisicaoHabDTO;
import com.siv.projetoc.tarefa.dto.TarefaCriarDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class ProjetocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetocApplication.class, args);
    }

    @Bean
    public CommandLineRunner inicializarTarefaTeste(TarefaService tarefaService) {

        return args -> {
            // monta o DTO da tarefa de teste
            TarefaCriarDTO dto = new TarefaCriarDTO();
            dto.setNome("Mutirão de saúde e reforço");
            dto.setDescricao("Atendimento de saúde e reforço escolar");
            dto.setData(LocalDate.of(2026, 11, 7));   // sábado
            dto.setHorarioInicio(LocalTime.of(9, 0));
            dto.setHorarioFim(LocalTime.of(12, 0));
            dto.setRua("Rua Central");
            dto.setBairro("Centro");
            dto.setCidade("Passo Fundo");
            dto.setEstado("RS");

// requisição 1: 2 voluntários com Educação (id = 2)
            RequisicaoHabDTO reqEducacao = new RequisicaoHabDTO();
            reqEducacao.setHabilidadeId(2L);
            reqEducacao.setQuantidade(1);

// requisição 2: 2 voluntários com Saúde (id = 5)
            RequisicaoHabDTO reqSaude = new RequisicaoHabDTO();
            reqSaude.setHabilidadeId(5L);
            reqSaude.setQuantidade(1);

            dto.setRequisicaoHab(List.of(reqEducacao, reqSaude));  // as duas habilidades na mesma tarefa

            tarefaService.salvarComRequisicaoHabilidade(dto, 21L);
        };
    }
}