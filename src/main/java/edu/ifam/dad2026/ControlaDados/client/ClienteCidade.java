package edu.ifam.dad2026.ControlaDados.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteCidade {

    private static final String BASE_URL = "http://localhost:8081/api/cidade";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        try {
            System.out.println("=== INICIANDO TESTES DO WEBSERVICE CIDADE ===\n");

            String novaCidadeJson = """
                    {
                        "nome": "Joinville",
                        "ibge": "4209102",
                        "estadoIbge": "42"
                    }
                    """;

            cadastrarCidade(novaCidadeJson);

            listarCidades();

            buscarCidadePorIbge("4209102");

            String cidadeAtualizadaJson = """
                    {
                        "nome": "Joinville Atualizada",
                        "ibge": "4209102",
                        "estadoIbge": "42"
                    }
                    """;

            atualizarCidade(4L, cidadeAtualizadaJson);

            buscarCidadePorIbge("4209102");

            // deletarCidade(4L);

            listarCidades();

        } catch (Exception e) {
            System.out.println("Erro ao executar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cadastrarCidade(String json) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [POST] Cadastrando cidade ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void listarCidades() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [GET] Listando cidades ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void buscarCidadePorIbge(String ibge) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + ibge))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [GET] Buscando cidade por IBGE ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void atualizarCidade(Long id, String json) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [PUT] Atualizando cidade ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }

    public static void deletarCidade(Long id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("--- [DELETE] Deletando cidade ---");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        System.out.println();
    }
}