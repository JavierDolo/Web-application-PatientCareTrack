package patientcaretrackbackend.domain.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private Role role;
    private Set<Long> pacientesIds;
}
