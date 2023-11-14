package br.com.brunood.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateCompanyDTO {

    private String username;
    private String password;
}
