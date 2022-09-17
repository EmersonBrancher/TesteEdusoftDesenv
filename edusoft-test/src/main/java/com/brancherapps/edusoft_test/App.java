package com.brancherapps.edusoft_test;


import java.util.ArrayList;

import com.brancherapps.edusoft_test.controller.AlunoController;
import com.brancherapps.edusoft_test.model.entities.Aluno;
import com.brancherapps.edusoft_test.model.entities.AlunosResponse;
import com.brancherapps.edusoft_test.model.service.EdusoftAPI;
import com.brancherapps.edusoft_test.model.service.exceptions.RequestException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class App {
    public static void main( String[] args ) throws JsonProcessingException {
		AlunoController alunoController = new AlunoController();
		ArrayList<AlunosResponse> resultados = new ArrayList<AlunosResponse>();
		ArrayList<Aluno> listaAlunos = null;
		try {
			listaAlunos = new EdusoftAPI().getAlunos();
			for (Aluno aluno : listaAlunos) {
				aluno.setMedia(alunoController.calcularMedia(aluno.getNotas()));
				int totalFaltas = alunoController.calcularTotalFaltas(aluno.getNotas());
				double percentualFrequencia = alunoController.calcularFrequencia(aluno.getTotalAulas(), totalFaltas);
				aluno.setResultado(alunoController.calcularResultado(aluno.getMedia(), percentualFrequencia));

				resultados.add(new AlunosResponse(aluno.getMedia(), aluno.getResultado(), aluno.getCodigo()));
			}
		} catch (RequestException e) {
			e.printStackTrace();
		}
		String teste = new EdusoftAPI().montaJsonResponse(resultados);
		System.out.println(teste);

	}

}
