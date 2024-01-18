package airborne.controller;

import airborne.business.CreateReportUseCase;
import airborne.business.GetReportsUseCase;
import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;
import airborne.business.dto.GetReportsResponse;
import airborne.configuration.security.token.AccessToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true" )
@AllArgsConstructor
public class ReportController {
    private final CreateReportUseCase createReportUseCase;
    private final GetReportsUseCase getReportsUseCase;

    @Autowired
    private AccessToken authenticatedUser;

    @PostMapping()
    public ResponseEntity<CreateReportResponse> createReport(@RequestBody @Valid CreateReportRequest request){
        long authenticatedUserId = authenticatedUser.getUserId();

        if(request.getReporterId() != authenticatedUserId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CreateReportResponse response = createReportUseCase.createReport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping()
    public ResponseEntity<GetReportsResponse> getReports(){
        GetReportsResponse response = getReportsUseCase.getReports();
        return ResponseEntity.ok(response);
    }
}
