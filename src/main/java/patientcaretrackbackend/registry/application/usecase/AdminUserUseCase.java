package patientcaretrackbackend.registry.application.usecase;

import patientcaretrackbackend.registry.application.dto.UserSummaryDto;

import java.util.List;

public interface AdminUserUseCase {

    List<UserSummaryDto> all();

    UserSummaryDto get(Long id);

    void resetPassword(Long id, String newPassword);
}
