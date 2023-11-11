package br.com.brunood.gestao_vagas.modules.company.controllers;

import br.com.brunood.gestao_vagas.modules.company.entity.Company;
import br.com.brunood.gestao_vagas.modules.company.usecases.CreateCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(Company data) {
        try {
            Company result = createCompanyUseCase.execute(data);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
