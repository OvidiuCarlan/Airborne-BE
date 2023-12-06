package airborne.business.impl;

import airborne.domain.Friendship;
import airborne.persistance.entity.FriendshipEntity;

public class FriendshipConverter {

    private FriendshipConverter(){

    }
    public static Friendship convert(FriendshipEntity friendship){
        return Friendship.builder()
                .id(friendship.getId())
                .senderId(friendship.getSenderId())
                .recipientId(friendship.getRecipientId())
                .status(friendship.getStatus())
                .build();
    }
}
