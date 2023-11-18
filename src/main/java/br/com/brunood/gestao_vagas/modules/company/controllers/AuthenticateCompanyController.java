package br.com.brunood.gestao_vagas.modules.company.controllers;

import br.com.brunood.gestao_vagas.modules.company.dto.AuthenticateCompanyDTO;
import br.com.brunood.gestao_vagas.modules.company.dto.AuthenticateCompanyResponseDTO;
import br.com.brunood.gestao_vagas.modules.company.usecases.AuthenticateCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/company")
public class AuthenticateCompanyController {

    @Autowired
    private AuthenticateCompanyUseCase authenticateCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticateCompanyDTO data) throws AuthenticationException {
        try {
            AuthenticateCompanyResponseDTO token = this.authenticateCompanyUseCase.execute(data);

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
