package br.com.brunood.gestao_vagas.modules.company.usecases;

import br.com.brunood.gestao_vagas.exceptions.UserFoundException;
import br.com.brunood.gestao_vagas.modules.company.entity.Company;
import br.com.brunood.gestao_vagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public Company execute(Company data) {
        this.companyRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(data);
    }

}
