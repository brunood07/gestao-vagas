package br.com.brunood.gestao_vagas.modules.candidate.controllers;

import br.com.brunood.gestao_vagas.modules.candidate.entity.Candidate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody Candidate data) {
        System.out.println(data);
    }

}
