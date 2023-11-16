package br.com.brunood.gestao_vagas.modules.candidate.controllers;

import br.com.brunood.gestao_vagas.modules.candidate.dto.AuthenticateCandidateRequestDTO;
import br.com.brunood.gestao_vagas.modules.candidate.usecases.AuthenticateCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthenticateCandidateController {

    @Autowired
    private AuthenticateCandidateUseCase authenticateCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthenticateCandidateRequestDTO data) {

        try {
            var token = this.authenticateCandidateUseCase.execute(data);

            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }
    }
}
