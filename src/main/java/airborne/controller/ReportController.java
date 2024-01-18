package airborne.controller;

import airborne.business.CreateReportUseCase;
import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;
import airborne.configuration.security.token.AccessToken;
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
}
