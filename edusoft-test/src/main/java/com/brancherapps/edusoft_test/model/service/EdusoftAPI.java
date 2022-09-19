package com.brancherapps.edusoft_test.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import com.brancherapps.edusoft_test.model.entities.Aluno;
import com.brancherapps.edusoft_test.model.entities.AlunosRequest;
import com.brancherapps.edusoft_test.model.entities.AlunosResponse;
import com.brancherapps.edusoft_test.model.entities.ResultadoResponse;
import com.brancherapps.edusoft_test.model.service.enums.Result;
import com.brancherapps.edusoft_test.model.service.exceptions.RequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EdusoftAPI {
    private final String URL_TOKEN = "https://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token";
	private final String URL_REQUEST = "https://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute";
	private final String USUARIO = "mentor";
	private final String SENHA = "123456";
	private final String CONTENT_TYPE = "content-type";
	private final String APPLICATION_JSON = "application/json";
	private final String TOKEN = "token";
	private final String SERVICE_RECUPERA_ALUNOS = "/recuperaAlunos";
	private final String SERVICE_GRAVA_RESULTADO = "/gravaResultado";
	
	
	private String getToken(String servico) throws RequestException {
		OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
         .url(URL_TOKEN + servico)
         .get()
         .addHeader("usuario", USUARIO)
         .addHeader("senha", SENHA)
         .build();

        String token = "";
        try {
			Response response = client.newCall(request).execute();
			token = Objects.requireNonNull(response.body()).string();
		} catch (IOException e) {
			throw new RequestException("Erro ao buscar o token de autenticação no servico " + servico + ". Erro: " + e.getMessage());
		}
		
		return token;
	}
	
	public ArrayList<Aluno> getAlunos() throws RequestException {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");		
		RequestBody body = RequestBody.create("", mediaType);
		Response response;
		String token = this.getToken(SERVICE_RECUPERA_ALUNOS);
        Request request = new Request.Builder()
         .url(URL_REQUEST + SERVICE_RECUPERA_ALUNOS)
         .post(body)
         .addHeader(CONTENT_TYPE, APPLICATION_JSON)
         .addHeader(TOKEN, token)
         .build();
        
        try {
			response = client.newCall(request).execute();
			ObjectMapper mapper = new ObjectMapper();
			AlunosRequest[] alunosRequests = mapper.readValue(Objects.requireNonNull(response.body()).string(), AlunosRequest[].class);
			if (alunosRequests.length > 0) {
				if (alunosRequests[0].getResultado().equals(Result.Sucesso.toString())) {
					return alunosRequests[0].getAlunos();
				}
			}
			
		} catch (IOException e) {
			throw new RequestException("Erro ao consultar alunos. Erro: " + e.getMessage());
		}
        
		return new ArrayList<Aluno>();
        
	}

	public String enviarAlunos(ArrayList<AlunosResponse> resultado) throws RequestException, JsonProcessingException {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(this.montaJsonResponse(resultado), mediaType);
		String token = this.getToken(SERVICE_GRAVA_RESULTADO);
		Request request = new Request.Builder()
				.url(URL_REQUEST + SERVICE_GRAVA_RESULTADO)
				.post(body)
				.addHeader(CONTENT_TYPE, APPLICATION_JSON)
				.addHeader(TOKEN, token)
				.build();

		try {
			Response response = client.newCall(request).execute();
			ObjectMapper mapper = new ObjectMapper();
			ResultadoResponse resultadoResponse = mapper.readValue(Objects.requireNonNull(response.body()).string(), ResultadoResponse.class);

			return resultadoResponse.toString();

		} catch (IOException e) {
			throw new RequestException("Erro ao gravar dados. Erro: " + e.getMessage());
		}

	}

	private String montaJsonResponse(ArrayList<AlunosResponse> relacaoAlunos) throws JsonProcessingException {
		LinkedHashMap<String, String> resultadoMap = new LinkedHashMap<>();
		LinkedHashMap<String, Object> relacaoAlunosMap = new LinkedHashMap<>();
		ArrayList<String> resultadoAlunos = new ArrayList<>();
		for (AlunosResponse aluno : relacaoAlunos) {
			relacaoAlunosMap.put("MEDIA", aluno.getMedia());
			relacaoAlunosMap.put("RESULTADO", aluno.getResultado().toString());
			relacaoAlunosMap.put("COD_ALUNO", aluno.getCodigoAluno());
			relacaoAlunosMap.put("SEU_NOME", aluno.getAutor());

			ObjectMapper mapper = new ObjectMapper();
			try {
				String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(relacaoAlunosMap);
				resultadoAlunos.add(result);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}

		}
		resultadoMap.put("\"resultadoAluno\"", resultadoAlunos.toString());
		return  resultadoMap.toString().replace("=", ":");
	}


}
