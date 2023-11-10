package br.com.brunood.gestao_vagas.modules.candidate.repository;

import br.com.brunood.gestao_vagas.modules.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
}
