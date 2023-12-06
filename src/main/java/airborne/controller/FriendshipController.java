package airborne.controller;

import airborne.business.AddFriendUseCase;
import airborne.business.CheckFriendshipStatusUseCase;
import airborne.business.dto.AddFriendRequest;
import airborne.business.dto.AddFriendResponse;
import airborne.business.dto.CheckFriendshipStatusRequest;
import airborne.business.dto.CheckFriendshipStatusResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendships")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@AllArgsConstructor

public class FriendshipController {
    private final AddFriendUseCase addFriendUseCase;
    private final CheckFriendshipStatusUseCase checkFriendshipStatusUseCase;

    @PostMapping
    public ResponseEntity<AddFriendResponse> createFriendship(@RequestBody @Valid AddFriendRequest request){
        AddFriendResponse response = addFriendUseCase.addFriend(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(path = "/check/{loggedInUserId}/{otherUserId}")
    public ResponseEntity<CheckFriendshipStatusResponse> checkFriendshipStatus(
            @PathVariable("loggedInUserId") Long loggedInUserId,
            @PathVariable("otherUserId") Long otherUserId){

        CheckFriendshipStatusRequest request = new CheckFriendshipStatusRequest(loggedInUserId, otherUserId);
        CheckFriendshipStatusResponse response = checkFriendshipStatusUseCase.checkFriendshipStatus(request);

        return ResponseEntity.ok(response);
    }
}
