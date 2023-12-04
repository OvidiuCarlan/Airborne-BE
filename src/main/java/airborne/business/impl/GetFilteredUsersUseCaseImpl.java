package airborne.business.impl;

import airborne.business.GetFilteredUsersUseCase;
import airborne.business.dto.GetFilteredUsersRequest;
import airborne.business.dto.GetFilteredUsersResponse;
import airborne.domain.User;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetFilteredUsersUseCaseImpl implements GetFilteredUsersUseCase {

    private final UserRepository userRepository;

    @Override
    public GetFilteredUsersResponse getFilteredUsers(GetFilteredUsersRequest request){
        List<UserEntity> results;

        if(request != null){
            results = userRepository.filteredSearch(request.getUsername());
        }
        else{
            results = new ArrayList<>();
        }
        final GetFilteredUsersResponse response = new GetFilteredUsersResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setFilteredSearchUsers(users);

        return response;
     }
}
