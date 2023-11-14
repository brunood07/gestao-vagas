package br.com.brunood.gestao_vagas.modules.company.controllers;

import br.com.brunood.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.brunood.gestao_vagas.modules.company.entity.Job;
import br.com.brunood.gestao_vagas.modules.company.usecases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO data, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var payload = Job.builder()
                .benefits(data.getBenefits())
                .description(data.getDescription())
                .level(data.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

        return ResponseEntity.ok().body(this.createJobUseCase.execute(payload));
    }
}
