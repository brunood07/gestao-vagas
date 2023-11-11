package br.com.brunood.gestao_vagas.modules.company.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String beneficios;
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
