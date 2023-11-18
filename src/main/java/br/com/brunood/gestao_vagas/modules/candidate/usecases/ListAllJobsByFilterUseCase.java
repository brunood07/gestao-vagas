package br.com.brunood.gestao_vagas.modules.candidate.usecases;

import br.com.brunood.gestao_vagas.modules.company.entity.Job;
import br.com.brunood.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> execute(String description) {
       return this.jobRepository.findByDescriptionContaining(description);
    }
}
