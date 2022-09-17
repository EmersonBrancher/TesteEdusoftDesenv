package com.brancherapps.edusoft_test.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultadoResponse {
    @JsonProperty("resultado")
    private String resultado;
    @JsonProperty("linhasAfetadas")
    private int linhasAfetadas;

}
