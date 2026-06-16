package edu.ifam.dad2026.ControlaDados.service;

import edu.ifam.dad2026.ControlaDados.Repository.EstadoRepository;
import edu.ifam.dad2026.ControlaDados.dto.EstadoInputDto;
import edu.ifam.dad2026.ControlaDados.dto.EstadoOutputDto;
import edu.ifam.dad2026.ControlaDados.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<EstadoOutputDto> list() {
        return estadoRepository.findAll()
                .stream()
                .map(EstadoOutputDto::new)
                .toList();
    }

    public Optional<EstadoOutputDto> getById(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isPresent()) {
            return Optional.of(new EstadoOutputDto(estado.get()));
        }

        return Optional.empty();
    }

    public EstadoOutputDto create(EstadoInputDto estadoInputDto) {
        Estado estado = estadoInputDto.build();
        Estado estadoSalvo = estadoRepository.save(estado);
        return new EstadoOutputDto(estadoSalvo);
    }

    public Optional<EstadoOutputDto> update(Long id, EstadoInputDto estadoInputDto) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);

        if (estadoOptional.isPresent()) {
            Estado estado = estadoInputDto.build();
            estado.setId(id);

            Estado estadoSalvo = estadoRepository.save(estado);
            return Optional.of(new EstadoOutputDto(estadoSalvo));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isPresent()) {
            estadoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
