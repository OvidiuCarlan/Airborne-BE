package airborne.business.impl;

import airborne.business.GetReportsUseCase;
import airborne.business.dto.GetReportsResponse;
import airborne.domain.Report;
import airborne.persistance.ReportRepository;
import airborne.persistance.entity.ReportEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReportsUseCaseImpl implements GetReportsUseCase {

    private final ReportRepository reportRepository;
    @Override
    public GetReportsResponse getReports() {
        List<ReportEntity> results = reportRepository.findAll();

        final GetReportsResponse response = new GetReportsResponse();
        List<Report> reports = results
                .stream()
                .map(ReportConverter::convert)
                .toList();
        response.setReports(reports);

        return response;
    }
}
