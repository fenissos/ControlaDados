package edu.ifam.dad2026.ControlaDados.Repository;

import edu.ifam.dad2026.ControlaDados.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNome(String nome);
}
