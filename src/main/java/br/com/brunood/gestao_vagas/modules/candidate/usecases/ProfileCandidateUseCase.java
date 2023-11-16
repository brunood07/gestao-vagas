package br.com.brunood.gestao_vagas.modules.candidate.usecases;

import br.com.brunood.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.brunood.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();
    }
}
