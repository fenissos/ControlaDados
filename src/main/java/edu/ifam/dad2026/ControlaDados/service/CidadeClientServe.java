package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CidadeClientServe {

    @Value("${api.cidade.url}")
    private String cidadeApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public CidadeOutputDto buscarPorIbge(String ibge) {

        if (ibge == null || ibge.isBlank()) {
            return null;
        }

        try {
            return restTemplate.getForObject(
                    cidadeApiUrl + "/" + ibge,
                    CidadeOutputDto.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Não foi possível consultar a API de Cidade",
                    e
            );
        }
    }
}
