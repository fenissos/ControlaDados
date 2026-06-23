package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class EstadoClientServe {

    @Value("${api.estado.url}")
    private String estadoApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public EstadoOutputDto buscarPorIbge(String ibge) {

        if (ibge == null || ibge.isBlank()) {
            return null;
        }

        EstadoOutputDto[] estados;

        try {
            estados = restTemplate.getForObject(
                    estadoApiUrl,
                    EstadoOutputDto[].class
            );
        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Não foi possível consultar a API de Estado",
                    e
            );
        }

        if (estados != null) {
            for (EstadoOutputDto estado : estados) {
                if (ibge.equals(estado.getIbge())) {
                    return estado;
                }
            }
        }

        return null;
    }
}
