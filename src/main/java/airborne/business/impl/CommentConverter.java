package airborne.business.impl;

import airborne.domain.Comment;
import airborne.persistance.entity.CommentEntity;

public class CommentConverter {
    private CommentConverter(){

    }
    public static Comment convert(CommentEntity comment){
        return Comment.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .build();
    }
}
