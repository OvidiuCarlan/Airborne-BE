package airborne.business.impl;

import airborne.business.CreateReportUseCase;
import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;
import airborne.persistance.ReportRepository;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.ReportEntity;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateReportUseCaseImpl implements CreateReportUseCase {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Override
    public CreateReportResponse createReport(CreateReportRequest request) {
        ReportEntity savedReport = savedNewReport(request);

        if(savedReport != null){
            return CreateReportResponse.builder()
                    .id(savedReport.getId())
                    .build();
        } else {
            return CreateReportResponse.builder()
                    .build();
        }
    }

    private ReportEntity savedNewReport(CreateReportRequest request){
        Optional <UserEntity> reporter = userRepository.findById(request.getReporterId());
        Optional <UserEntity> reported = userRepository.findById(request.getReportedId());

        if(reporter.isPresent() && reported.isPresent()){
            ReportEntity newReport = ReportEntity.builder()
                    .reporter(reporter.get())
                    .reported(reported.get())
                    .reason(request.getReason())
                    .build();
            return reportRepository.save(newReport);
        } else {
            return null;
        }
    }
}
