package airborne.business.impl;

import airborne.business.AddFriendUseCase;
import airborne.business.dto.AddFriendRequest;
import airborne.business.dto.AddFriendResponse;
import airborne.persistance.FriendshipRepository;
import airborne.persistance.entity.FriendshipEntity;
import airborne.persistance.entity.FriendshipEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddFriendUseCaseImplTest {
    @Mock
    private FriendshipRepository friendshipRepository;
    @InjectMocks
    private AddFriendUseCaseImpl addFriendUseCase;

    @Test
    void addFriend_Success(){
        AddFriendRequest request = new AddFriendRequest(1L, 2L);
        FriendshipEntity newFriendship = FriendshipEntity.builder()
                .senderId(request.getSenderId())
                .recipientId(request.getRecipientId())
                .status(FriendshipEnum.ACCEPTED)
                .build();

        when(friendshipRepository.save(any())).thenReturn(newFriendship);

        //Act
        AddFriendResponse response = addFriendUseCase.addFriend(request);

        //Assert
        verify(friendshipRepository, times(1)).save(any());
    }
}