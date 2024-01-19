package airborne.business.impl;

import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;
import airborne.persistance.ReportRepository;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.ReportEntity;
import airborne.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CreateReportUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private CreateReportUseCaseImpl createReportUseCase;

    @Test
    void createReport_Success() {
        // Arrange
        Long reporterId = 1L;
        Long reportedId = 2L;
        String reason = "Inappropriate content";

        CreateReportRequest request = new CreateReportRequest(reporterId, reportedId, reason);

        UserEntity reporter = new UserEntity();
        reporter.setId(reporterId);

        UserEntity reported = new UserEntity();
        reported.setId(reportedId);

        when(userRepository.findById(reporterId)).thenReturn(Optional.of(reporter));
        when(userRepository.findById(reportedId)).thenReturn(Optional.of(reported));

        ReportEntity savedReport = new ReportEntity();
        savedReport.setId(1L);

        when(reportRepository.save(any(ReportEntity.class))).thenReturn(savedReport);

        // Act
        CreateReportResponse response = createReportUseCase.createReport(request);

        // Assert
        verify(userRepository, times(2)).findById(anyLong());
        verify(reportRepository, times(1)).save(any(ReportEntity.class));

        assertEquals(savedReport.getId(), response.getId());
    }

    @Test
    void createReport_UserNotFound() {
        // Arrange
        Long reporterId = 1L;
        Long reportedId = 2L;
        String reason = "Inappropriate content";

        CreateReportRequest request = new CreateReportRequest(reporterId, reportedId, reason);

        when(userRepository.findById(reporterId)).thenReturn(Optional.empty());
        when(userRepository.findById(reportedId)).thenReturn(Optional.empty());

        // Act
        CreateReportResponse response = createReportUseCase.createReport(request);

        // Assert
        verify(userRepository, times(1)).findById(reporterId);
        verify(userRepository, times(1)).findById(reportedId);
        verify(reportRepository, never()).save(any(ReportEntity.class));

        assertEquals(null, response.getId());
    }
}