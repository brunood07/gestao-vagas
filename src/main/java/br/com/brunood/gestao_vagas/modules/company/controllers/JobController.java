package br.com.brunood.gestao_vagas.modules.company.controllers;

import br.com.brunood.gestao_vagas.modules.company.entity.Job;
import br.com.brunood.gestao_vagas.modules.company.usecases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Job data) {
        Job result = this.createJobUseCase.execute(data);
        return ResponseEntity.ok().body(result);
    }
}
