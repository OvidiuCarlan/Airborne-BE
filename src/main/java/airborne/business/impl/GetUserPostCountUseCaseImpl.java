package airborne.business.impl;

import airborne.business.GetUserPostCountUseCase;
import airborne.business.dto.GetUserPostCountResponse;
import airborne.persistance.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserPostCountUseCaseImpl implements GetUserPostCountUseCase {
    private final PostRepository postRepository;

    @Override
    public GetUserPostCountResponse getUserPostCount(){
        List<airborne.domain.UserPostCount> getUserPostCount = postRepository.getUserPostCounts();

        final GetUserPostCountResponse response = new GetUserPostCountResponse();
        response.setUserPostCounts(getUserPostCount);
        return response;
    }
}
