package airborne.business.impl;

import airborne.domain.User;
import airborne.persistance.entity.UserEntity;

public class UserConverter {

    private UserConverter(){

    }

    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
