package airborne.business;

import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;

public interface CreateReportUseCase {
    CreateReportResponse createReport(CreateReportRequest request);
}
