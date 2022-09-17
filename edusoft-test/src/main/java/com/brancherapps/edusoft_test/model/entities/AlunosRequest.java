package com.brancherapps.edusoft_test.model.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlunosRequest {
	
	@JsonProperty("resultado")
	private String resultado;
	
	@JsonProperty("alunos")
	private ArrayList<Aluno> alunos;
	
	@JsonCreator
	public AlunosRequest(@JsonProperty("resultado") String resultado,
						 @JsonProperty("alunos") ArrayList<Aluno> alunos) {
		setResultado(resultado);
		setAlunos(alunos);
	}
}
