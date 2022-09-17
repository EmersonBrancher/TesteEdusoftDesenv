package com.brancherapps.edusoft_test.model.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Aluno {

	@JsonProperty("COD")
    private int codigo;
	
	@JsonProperty("NOME")
    private String nome;
	
	@JsonProperty("TOTAL_AULAS")
    private int totalAulas;
	
	@JsonProperty("nota")
    private ArrayList<Nota> notas;

    @JsonIgnore
    private double media;

    @JsonIgnore
    private String resultado;

}
