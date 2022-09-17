package com.brancherapps.edusoft_test.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlunosResponse {
    @JsonProperty("MEDIA")
    private double media;
    @JsonProperty("RESULTADO")
    private String resultado;
    @JsonProperty("COD_ALUNO")
    private int codigoAluno;
    @JsonProperty("SEU_NOME")
    private final String autor = "Emerson Brancher";

    public AlunosResponse(double media, String resultado, int codigo) {
        setMedia(media);
        setResultado(resultado);
        setCodigoAluno(codigo);
    }
}
