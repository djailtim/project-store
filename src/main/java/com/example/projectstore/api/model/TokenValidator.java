package com.example.projectstore.api.model;

import com.example.projectstore.api.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_tokens")
@Builder
@Log4j2
public class TokenValidator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId;

    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean expired;

}
