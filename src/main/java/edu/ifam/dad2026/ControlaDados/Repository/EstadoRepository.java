package edu.ifam.dad2026.ControlaDados.Repository;

import edu.ifam.dad2026.ControlaDados.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByIbge(String ibge);
}