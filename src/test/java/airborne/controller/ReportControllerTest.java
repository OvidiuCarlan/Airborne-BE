package airborne.controller;

import airborne.business.CreateReportUseCase;
import airborne.business.GetReportsUseCase;
import airborne.business.dto.CreateReportRequest;
import airborne.business.dto.CreateReportResponse;
import airborne.business.dto.GetReportsResponse;
import airborne.configuration.security.token.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class ReportControllerTest {
    @Mock
    private CreateReportUseCase createReportUseCase;

    @Mock
    private GetReportsUseCase getReportsUseCase;

    @Mock
    private AccessToken authenticatedUser;

    @InjectMocks
    private ReportController reportController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void createReport_Success() throws Exception {
        long reporterId = 1L;

        CreateReportRequest request = new CreateReportRequest();
        request.setReporterId(1L);
        request.setReason("Valid Reason");
        request.setReportedId(2L);  // Provide a valid reportedId

        when(authenticatedUser.getUserId()).thenReturn(reporterId);
        when(createReportUseCase.createReport(request)).thenReturn(new CreateReportResponse());

        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))  // Serialize the request object to JSON
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void createReport_ValidationFailure() throws Exception {
        mockMvc.perform(post("/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"invalidField\": \"invalidValue\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "ADMIN")
    void getReports_Success() throws Exception {
        when(getReportsUseCase.getReports()).thenReturn(new GetReportsResponse());

        mockMvc.perform(get("/reports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reports").exists());
    }
}