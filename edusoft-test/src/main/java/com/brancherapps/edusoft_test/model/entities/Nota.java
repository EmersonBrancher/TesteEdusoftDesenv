package com.brancherapps.edusoft_test.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Nota {
    
	@JsonProperty("FALTAS")
    private int faltas;
	
    @JsonProperty("NOTA")    
    private double nota;
    
	@JsonCreator
	public Nota(@JsonProperty("NOTA") double nota, 
				@JsonProperty("FALTAS") int faltas) {
		setNota(nota);
		setFaltas(faltas);
	}
	
	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}    

}
