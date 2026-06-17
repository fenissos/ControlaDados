package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.dto.CidadeOutputDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CidadeClientServe {

    @Value("${api.cidade.url}")
    private String cidadeApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public CidadeOutputDto buscarPorIbge(String ibge) {

        return restTemplate.getForObject(
                cidadeApiUrl + "/" + ibge,
                CidadeOutputDto.class
        );
    }
}