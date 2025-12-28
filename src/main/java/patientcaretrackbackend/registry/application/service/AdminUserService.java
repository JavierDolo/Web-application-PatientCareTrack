package patientcaretrackbackend.registry.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.registry.application.dto.UserSummaryDto;
import patientcaretrackbackend.registry.application.usecase.AdminUserUseCase;
import patientcaretrackbackend.registry.domain.port.UserRepository;
import patientcaretrackbackend.shared.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService implements AdminUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserSummaryDto> all() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserSummaryDto(u.getId(), u.getUsername(), u.getRole().name()))
                .toList();
    }

    @Override
    public UserSummaryDto get(Long id) {
        var u = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));

        return new UserSummaryDto(u.getId(), u.getUsername(), u.getRole().name());
    }

    @Override
    public void resetPassword(Long id, String newPassword) {
        var u = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));

        // IMPORTANTE: guardamos hash, nunca el password plano
        u.setPasswordHash(passwordEncoder.encode(newPassword));

        userRepository.save(u);
    }
}
