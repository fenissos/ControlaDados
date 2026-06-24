package edu.ifam.dad2026.ControlaDados.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientePessoa {

    private static final String BASE_URL = "http://localhost:8081/api/pessoa";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        try {
            System.out.println("=== INICIANDO TESTES DO WEBSERVICE PESSOA ===\n");

            String novaPessoaJson = """
        {
            "nome": "Joao",
            "cpf": "98765432100",
            "email": "joao@email.com",
            "sexo": 0,
            "cidadeIbge": "1302603"
        }
        """;

            cadastrarPessoa(novaPessoaJson);

            listarPessoas();

            buscarPessoaPorId(1L);

            String pessoaAtualizadaJson = """
        {
            "nome": "Joao Atualizado",
            "cpf": "98765432100",
            "email": "joao.atualizado@email.com",
            "sexo": 0,
            "cidadeIbge": "1302603"
        }
        """;

            atualizarPessoa(1L, pessoaAtualizadaJson);

            buscarPessoaPorId(1L);

            // deletarPessoa(1L);

            listarPessoas();

        } catch (Exception e) {
            System.out.println("Erro ao executar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cadastrarPessoa(String json) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [POST] Cadastrando pessoa ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void listarPessoas() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [GET] Listando pessoas ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void buscarPessoaPorId(Long id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [GET] Buscando pessoa por ID ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void atualizarPessoa(Long id, String json) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [PUT] Atualizando pessoa ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void deletarPessoa(Long id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [DELETE] Deletando pessoa ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }
}
