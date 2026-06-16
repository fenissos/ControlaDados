package edu.ifam.dad2026.ControlaDados.Repository;

import edu.ifam.dad2026.ControlaDados.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
