package patientcaretrackbackend.registry.infrastructure.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import patientcaretrackbackend.registry.domain.model.Role;
import patientcaretrackbackend.registry.domain.model.User;
import patientcaretrackbackend.registry.domain.port.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminBootstrap {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String adminUsername = "doctor";
        String adminPassword = "1234"; // cámbialo si quieres

        userRepository.findByUsername(adminUsername)
                .ifPresentOrElse(
                        user -> System.out.println(">>> SUPERADMIN ya existe: " + adminUsername),
                        () -> {
                            User admin = User.builder()
                                    .username(adminUsername)
                                    .passwordHash(passwordEncoder.encode(adminPassword))
                                    .role(Role.ADMIN)
                                    .build();
                            userRepository.save(admin);
                            System.out.println(">>> SUPERADMIN creado: " + adminUsername + " / " + adminPassword);
                        }
                );
    }
}
