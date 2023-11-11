package br.com.brunood.gestao_vagas.modules.candidate.usecases;

import br.com.brunood.gestao_vagas.exceptions.UserFoundException;
import br.com.brunood.gestao_vagas.modules.candidate.entity.Candidate;
import br.com.brunood.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    public Candidate execute(Candidate data) {
        this.candidateRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(data);
    }

}
