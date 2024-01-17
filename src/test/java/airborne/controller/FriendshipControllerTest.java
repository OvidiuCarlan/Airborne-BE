package airborne.controller;

import airborne.business.AddFriendUseCase;
import airborne.business.CheckFriendshipStatusUseCase;
import airborne.business.DeleteFriendshipUseCase;
import airborne.business.GetFriendListUseCase;
import airborne.business.dto.*;
import airborne.persistance.entity.FriendshipEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class FriendshipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddFriendUseCase addFriendUseCase;

    @MockBean
    private CheckFriendshipStatusUseCase checkFriendshipStatusUseCase;

    @MockBean
    private DeleteFriendshipUseCase deleteFriendshipUseCase;

    @MockBean
    private GetFriendListUseCase getFriendListUseCase;

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void createFriendship_Success() throws Exception {
        // Arrange
        AddFriendRequest request = new AddFriendRequest(1L, 2L);
        AddFriendResponse response = new AddFriendResponse(1L, FriendshipEnum.ACCEPTED);

        when(addFriendUseCase.addFriend(request)).thenReturn(response);

        // Act
        ResultActions result = mockMvc.perform(post("/friendships")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"senderId\":1,\"recipientId\":2}"));

        // Assert
        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("ACCEPTED"));

        verify(addFriendUseCase, times(1)).addFriend(request);
    }
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = "USER")
//    void checkFriendshipStatus_Success() throws Exception {
//        // Arrange
//        long loggedInUserId = 1L;
//        long otherUserId = 2L;
//        CheckFriendshipStatusRequest request = new CheckFriendshipStatusRequest(loggedInUserId, otherUserId);
//        CheckFriendshipStatusResponse response = new CheckFriendshipStatusResponse(1L, FriendshipEnum.ACCEPTED);
//
//        when(checkFriendshipStatusUseCase.checkFriendshipStatus(request)).thenReturn(response);
//
//        // Act
//        ResultActions result = mockMvc.perform(get("/friendships/check/{loggedInUserId}/{otherUserId}", loggedInUserId, otherUserId));
//
//        // Assert
//        result.andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.status").value("ACCEPTED"))
//                .andExpect(jsonPath("$.timestamp").isNotEmpty());
//
//        verify(checkFriendshipStatusUseCase, times(1)).checkFriendshipStatus(request);
//    }

//    @Test
//    void deleteFriendship_Success() throws Exception {
//        // Arrange
//        long friendshipId = 1L;
//
//        // Act
//        ResultActions result = mockMvc.perform(delete("/friendships/delete/{id}", friendshipId));
//
//        // Assert
//        result.andExpect(status().isNoContent());
//
//        verify(deleteFriendshipUseCase, times(1)).deleteFriendship(friendshipId);
//    }
//
//    @Test
//    void getFriendList_Success() throws Exception {
//        // Arrange
//        long userId = 1L;
//        GetFriendListRequest request = GetFriendListRequest.builder().id(userId).build();
//        GetFriendListResponse response = new GetFriendListResponse(/* your response fields here */);
//
//        when(getFriendListUseCase.getFriendList(request)).thenReturn(response);
//
//        // ACt
//        ResultActions result = mockMvc.perform(get("/friendships/all/{id}", userId));
//
//        // Assert
//        result.andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$./* your expected field in response here */").value(/* expected value */));
//
//        verify(getFriendListUseCase, times(1)).getFriendList(request);
//    }
}