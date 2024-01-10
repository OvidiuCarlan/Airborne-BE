package airborne.business.impl;

import airborne.business.GetFriendListUseCase;
import airborne.business.dto.GetFriendListRequest;
import airborne.business.dto.GetFriendListResponse;
import airborne.domain.User;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetFriendListUseCaseImpl implements GetFriendListUseCase {

    private final UserRepository userRepository;
    @Override
    public GetFriendListResponse getFriendList(GetFriendListRequest request) {
        List<UserEntity> results;

        if(request.getId() != null){
            results = userRepository.getFriendList(request.getId());
        }
        else {
            results = new ArrayList<>();
        }
        final GetFriendListResponse response = new GetFriendListResponse();
        List<User> friends = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setFriends(friends);

        return response;
    }
}
