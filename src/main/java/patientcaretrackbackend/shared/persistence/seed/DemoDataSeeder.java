package patientcaretrackbackend.shared.persistence.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import patientcaretrackbackend.patients.infrastructure.persistence.entity.PacienteEntity;
import patientcaretrackbackend.patients.infrastructure.persistence.spring.PacienteJpaRepository;
import patientcaretrackbackend.registry.domain.model.Role;
import patientcaretrackbackend.registry.domain.model.User;
import patientcaretrackbackend.registry.domain.port.UserRepository;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DemoDataSeeder implements CommandLineRunner {

    private static final String SEED_KEY = "demo-pacientes-v1";

    private final SeedRunJpaRepository seedRunJpaRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PacienteJpaRepository pacienteJpaRepository;

    @Override
    public void run(String... args) {

        //  Solo una vez
        if (seedRunJpaRepository.existsById(SEED_KEY)) return;

        // 1) Crear 2 users de demo (si no existen)
        User user1 = userRepository.findByUsername("user1")
                .orElseGet(() -> userRepository.save(User.builder()
                        .username("user1")
                        .passwordHash(passwordEncoder.encode("User1_1234"))
                        .role(Role.USER)
                        .build()));

        User user2 = userRepository.findByUsername("user2")
                .orElseGet(() -> userRepository.save(User.builder()
                        .username("user2")
                        .passwordHash(passwordEncoder.encode("User2_1234"))
                        .role(Role.USER)
                        .build()));

        // 2) Crear 4 pacientes (3 a user1, 1 a user2)
        pacienteJpaRepository.save(PacienteEntity.builder()
                .nombre("María Gómez")
                .edad(84)
                .historialMedico("HTA, artrosis.")
                .observacionesGenerales("Riesgo de caídas. Acompañar en paseos.")
                .imageUrl("https://picsum.photos/200?1")
                .assignedUserId(user1.getId())
                .build());

        pacienteJpaRepository.save(PacienteEntity.builder()
                .nombre("Antonio López")
                .edad(79)
                .historialMedico("Diabetes tipo 2.")
                .observacionesGenerales("Control de líquidos. Revisión pies.")
                .imageUrl("https://picsum.photos/200?2")
                .assignedUserId(user1.getId())
                .build());

        pacienteJpaRepository.save(PacienteEntity.builder()
                .nombre("Carmen Ruiz")
                .edad(90)
                .historialMedico("Deterioro cognitivo leve.")
                .observacionesGenerales("Vigilar ingesta. Rutina de sueño.")
                .imageUrl("https://picsum.photos/200?3")
                .assignedUserId(user1.getId())
                .build());

        pacienteJpaRepository.save(PacienteEntity.builder()
                .nombre("José Martín")
                .edad(87)
                .historialMedico("EPOC. Medicación inhalada.")
                .observacionesGenerales("Aseo asistido. Monitorizar deposiciones.")
                .imageUrl("https://picsum.photos/200?4")
                .assignedUserId(user2.getId())
                .build());

        // 3) Marcar seed como ejecutada
        seedRunJpaRepository.save(SeedRunEntity.builder()
                .seedKey(SEED_KEY)
                .executedAt(Instant.now())
                .build());

        System.out.println("Seed demo aplicada: " + SEED_KEY);
        System.out.println("Users demo: user1/User1_1234, user2/User2_1234");
    }
}
