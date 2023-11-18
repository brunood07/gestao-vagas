package br.com.brunood.gestao_vagas.modules.company.repository;

import br.com.brunood.gestao_vagas.modules.company.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findByDescriptionContainingIgnoreCase(String filter);
}
