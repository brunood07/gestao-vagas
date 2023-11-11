package br.com.brunood.gestao_vagas.modules.company.usecases;

import br.com.brunood.gestao_vagas.exceptions.UserFoundException;
import br.com.brunood.gestao_vagas.modules.company.entity.Job;
import br.com.brunood.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public Job execute(Job data) {
        return this.jobRepository.save(data);
    }

}
