package airborne.business.impl;

import airborne.domain.Post;
import airborne.persistance.entity.PostEntity;

public class PostConverter {
    private PostConverter(){

    }
    public static Post convert(PostEntity post){
        return Post.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .content(post.getContent())
                .image(post.getImage())
                .dateTime(post.getDateTime())
                .build();
    }
}
