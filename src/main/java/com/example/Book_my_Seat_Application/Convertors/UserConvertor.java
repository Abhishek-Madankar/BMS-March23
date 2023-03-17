package com.example.Book_my_Seat_Application.Convertors;

import com.example.Book_my_Seat_Application.Entities.UserEntity;
import com.example.Book_my_Seat_Application.EntryDtos.UserEntryDto;

public class UserConvertor {

    //static is kept to avoid calling via object/instances
    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){


        UserEntity userEntity = UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName())
                .build();

        return userEntity;
    }
}
