package com.brancherapps.edusoft_test.controller;

import com.brancherapps.edusoft_test.model.entities.Nota;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AlunoControllerTest {

    private AlunoController alunoController = new AlunoController();
    ArrayList<Nota> notas = new ArrayList<>();
    Nota nota1 = new Nota(7, 2);
    Nota nota2 = new Nota(6, 1);
    Nota nota3 = new Nota(8, 3);

    @Test
    public void calcularMedia(){
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);
        double resultado = alunoController.calcularMedia(notas);

        Assert.assertEquals(7, resultado, 0);
    }

    @Test
    public void calcularTotalFaltas(){
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);
        int resultado = alunoController.calcularTotalFaltas(notas);

        Assert.assertEquals(6, resultado);
    }

    @Test
    public void calcularFrequencia(){
        int totalAulas = 30;
        int totalFaltas = 6;
        double resultado = alunoController.calcularFrequencia(totalAulas, totalFaltas);

        Assert.assertEquals(80, resultado, 0);
    }

    @Test
    public void calcularResultado_AP(){
        double media = 8;
        double percentualFrequencia = 80;
        String resultado = alunoController.calcularResultado(media,percentualFrequencia);
        Assert.assertEquals("AP", resultado);
    }

    @Test
    public void calcularResultado_RM(){
        double media = 6;
        double percentualFrequencia = 80;
        String resultado = alunoController.calcularResultado(media,percentualFrequencia);
        Assert.assertEquals("RM", resultado);
    }

    @Test
    public void calcularResultado_RF(){
        double media = 6;
        double percentualFrequencia = 60;
        String resultado = alunoController.calcularResultado(media,percentualFrequencia);
        Assert.assertEquals("RF", resultado);
    }

    @Test
    public void apresentarNotas(){
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);
        StringBuilder resultado = alunoController.apresentarNotas(notas);
        Assert.assertNotNull(resultado);
        Assert.assertEquals("7.0, 6.0, 8.0", resultado.toString());
    }

}
