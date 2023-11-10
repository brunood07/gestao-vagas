package br.com.brunood.gestao_vagas.modules.candidate.controllers;

import br.com.brunood.gestao_vagas.exceptions.UserFoundException;
import br.com.brunood.gestao_vagas.modules.candidate.entity.Candidate;
import br.com.brunood.gestao_vagas.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public void create(@Valid @RequestBody Candidate data) {
        this.candidateRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent((user) -> {
                        throw new UserFoundException();
                });


        this.candidateRepository.save(data);
    }

}
