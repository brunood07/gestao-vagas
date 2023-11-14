package br.com.brunood.gestao_vagas.modules.company.usecases;

import br.com.brunood.gestao_vagas.exceptions.UserFoundException;
import br.com.brunood.gestao_vagas.modules.company.entity.Company;
import br.com.brunood.gestao_vagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company execute(Company data) {
        this.companyRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        String password = passwordEncoder.encode(data.getPassword());
        data.setPassword(password);

        return this.companyRepository.save(data);
    }

}
