package com.siv.projetoc;

import com.siv.projetoc.tarefa.Tarefa;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class test {
    /*
        // classe anônima
        Animal bicho = new Animal() {  // <-- isto é uma classe =o
            public void fazerSom() {
                System.out.println("Au");
            }
        };

        // lambda, mesma coisa
        Animal bicho = () -> System.out.println("Au");
    */
    static void main() {

        ArrayList<Task> tarefa = new ArrayList<>();
        tarefa.add(new Task("Tarefa 1", LocalTime.of(9, 0)));
        tarefa.add(new Task("Tarefa 2", LocalTime.of(12, 0)));
        tarefa.add(new Task("Tarefa 3", LocalTime.of(17, 0)));
        tarefa.add(new Task("Tarefa 4", LocalTime.of(20, 0)));

        System.out.println("Tarefas antes das 17 horas incluindo o horario 17: \n");
        List<Task> resultado = tarefa.stream()
                .filter(t -> !(t.horario.isAfter(LocalTime.of(17, 0))))
                .toList();

        resultado.forEach(t -> System.out.println(t.nome + " - " + t.horario));

        System.out.println("Tarefas depois das 17 horas incluindo o horario 17: \n");
        resultado = tarefa.stream()
                .filter(t -> !(t.horario.isBefore(LocalTime.of(17, 0))))
                .toList();

        resultado.forEach(t -> System.out.println(t.nome + " - " + t.horario));


    }
}
