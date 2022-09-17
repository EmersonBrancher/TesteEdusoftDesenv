package com.brancherapps.edusoft_test.model.service.enums;

public enum Result {
	Sucesso("SUCESSO"),
	Erro("ERRO"),
	;

	private final String resultado;
	Result(String resultado) {
		this.resultado = resultado; 
	}
	
	@Override
	public String toString() {
		return resultado;
	}
}