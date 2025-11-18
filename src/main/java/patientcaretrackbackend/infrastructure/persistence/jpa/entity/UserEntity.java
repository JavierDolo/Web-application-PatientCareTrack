package patientcaretrackbackend.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import patientcaretrackbackend.domain.model.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private Set<Long> pacientesIds = new HashSet<>();
}
