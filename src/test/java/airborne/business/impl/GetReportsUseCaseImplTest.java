package airborne.business.impl;

import airborne.business.dto.GetReportsResponse;
import airborne.domain.Report;
import airborne.persistance.ReportRepository;
import airborne.persistance.entity.ReportEntity;
import airborne.persistance.entity.RoleEnum;
import airborne.persistance.entity.UserEntity;
import airborne.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetReportsUseCaseImplTest {
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private GetReportsUseCaseImpl getReportsUseCase;

    @Test
    void getReports_Success() {
        // Arrange
        UserRoleEntity userRole = UserRoleEntity.builder().role(RoleEnum.USER).build();


        UserEntity reporter = new UserEntity(1L, "Reporter", "test@mail.com", "test", userRole);
        UserEntity reported = new UserEntity(2L, "Reported", "test@mail.com", "test", userRole);

        List<ReportEntity> reportEntities = List.of(
                new ReportEntity(1L, reporter, reported, "Inappropriate Content"),
                new ReportEntity(2L, reporter, reported, "Spam")
                // Add more report entities as needed
        );

        when(reportRepository.findAll()).thenReturn(reportEntities);

        // Act
        GetReportsResponse response = getReportsUseCase.getReports();

        // Assert
        verify(reportRepository, times(1)).findAll();

        List<ReportEntity> expectedEntities = reportEntities;
        List<Report> expectedReports = expectedEntities.stream()
                .map(ReportConverter::convert)
                .toList();

        assertEquals(expectedReports, response.getReports());
    }
}