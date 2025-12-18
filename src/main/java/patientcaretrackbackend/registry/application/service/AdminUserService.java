package patientcaretrackbackend.registry.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patientcaretrackbackend.registry.application.dto.UserSummaryDto;
import patientcaretrackbackend.registry.application.usecase.AdminUserUseCase;
import patientcaretrackbackend.registry.domain.port.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService implements AdminUserUseCase {

    private final UserRepository userRepository;

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
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        return new UserSummaryDto(u.getId(), u.getUsername(), u.getRole().name());
    }
}
