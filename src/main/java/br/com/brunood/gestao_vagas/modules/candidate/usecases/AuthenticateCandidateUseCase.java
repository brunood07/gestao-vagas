package br.com.brunood.gestao_vagas.modules.candidate.usecases;

import br.com.brunood.gestao_vagas.modules.candidate.dto.AuthenticateCandidateRequestDTO;
import br.com.brunood.gestao_vagas.modules.candidate.dto.AuthenticateCandidateResponseDTO;
import br.com.brunood.gestao_vagas.modules.candidate.repository.CandidateRepository;
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
public class AuthenticateCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticateCandidateResponseDTO execute(AuthenticateCandidateRequestDTO data) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(data.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect!");
                });

        var passwordMatches = this.passwordEncoder.matches(data.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .sign(algorithm);

        return AuthenticateCandidateResponseDTO.builder().access_token(token).expiresIn(expiresIn.toEpochMilli()).build();
    }
}
