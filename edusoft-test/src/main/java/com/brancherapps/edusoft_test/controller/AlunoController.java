package com.brancherapps.edusoft_test.controller;

import com.brancherapps.edusoft_test.model.entities.Nota;

import java.util.ArrayList;

public class AlunoController {

    public double calcularMedia(ArrayList<Nota> notas) {
        double media = 0;
        int contaNrNotas = 0;
        for (Nota nota : notas) {
            media += nota.getNota();
            contaNrNotas++;
        }
        media = media / contaNrNotas;
        return media;
    }

    public int calcularTotalFaltas(ArrayList<Nota> faltas) {
        int totalFaltas = 0;
        for (Nota nota : faltas) {
            totalFaltas += nota.getFaltas();
        }
        return totalFaltas;
    }

    public double calcularFrequencia(int totalAulas, int totalFaltas) {
        double total = 0;
        total = 100 - ((100 * totalFaltas) / totalAulas);
        return total > 0 ? total : 0;
    }


    public String calcularResultado(double media, double percentualFrequencia) {
        if (percentualFrequencia >= 70) {
            return media >= 7 ? "AP" : "RM";
        } else {
            return "RF";
        }
    }

    public StringBuilder apresentarNotas(ArrayList<Nota> notas) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Nota nota : notas) {
            stringBuilder.append(nota.getNota());
            stringBuilder.append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder;
    }
}
