package br.com.brunood.gestao_vagas.modules.company.usecases;

import br.com.brunood.gestao_vagas.modules.company.dto.AuthenticateCompanyDTO;
import br.com.brunood.gestao_vagas.modules.company.dto.AuthenticateCompanyResponseDTO;
import br.com.brunood.gestao_vagas.modules.company.entity.Company;
import br.com.brunood.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthenticateCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticateCompanyResponseDTO execute(AuthenticateCompanyDTO data) throws AuthenticationException {
        Company company = this.companyRepository.findByUsername(data.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username/password incorrect!");
                }
        );

        boolean passwordMatches = passwordEncoder.matches(data.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return AuthenticateCompanyResponseDTO.builder().access_token(token).expiresIn(expiresIn.toEpochMilli()).build();
    }
}
