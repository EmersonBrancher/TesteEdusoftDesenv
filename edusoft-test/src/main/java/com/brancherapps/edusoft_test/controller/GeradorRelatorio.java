package com.brancherapps.edusoft_test.controller;

import com.brancherapps.edusoft_test.model.entities.Aluno;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GeradorRelatorio {
    public void gerarRelatorio(ArrayList<Aluno> listaAlunos) {
        StringBuilder stringBuilder = new StringBuilder("Relação dos alunos \r\n");
        AlunoController alunoController = new AlunoController();
        for (Aluno aluno : listaAlunos) {
            stringBuilder.append("\r\nNome: " + aluno.getNome());
            stringBuilder.append("\r\nNotas: " + alunoController.apresentarNotas(aluno.getNotas()));
            stringBuilder.append("\r\nTotal de faltas: " + alunoController.calcularTotalFaltas(aluno.getNotas()));
            stringBuilder.append("\r\nMédia: " + new DecimalFormat("#.##").format(alunoController.calcularMedia(aluno.getNotas())));
            stringBuilder.append("\r\nResultado: " + aluno.getResultado());
            stringBuilder.append("\r\n");
        }

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./relatório.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
