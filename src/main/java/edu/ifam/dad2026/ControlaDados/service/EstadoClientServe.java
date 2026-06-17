package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EstadoClientServe {

    @Value("${api.estado.url}")
    private String estadoApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public EstadoOutputDto buscarPorIbge(String ibge) {

        EstadoOutputDto[] estados = restTemplate.getForObject(
                estadoApiUrl,
                EstadoOutputDto[].class
        );

        if (estados != null) {
            for (EstadoOutputDto estado : estados) {
                if (estado.getIbge().equals(ibge)) {
                    return estado;
                }
            }
        }

        return null;
    }
}