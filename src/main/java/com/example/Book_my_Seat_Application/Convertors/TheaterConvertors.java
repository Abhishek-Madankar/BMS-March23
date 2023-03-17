package com.example.Book_my_Seat_Application.Convertors;

import com.example.Book_my_Seat_Application.Entities.TheaterEntity;
import com.example.Book_my_Seat_Application.EntryDtos.TheatreEntryDto;

public class TheaterConvertors {

    public static TheaterEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto){

        return TheaterEntity.builder().name(theatreEntryDto.getName()).location(theatreEntryDto.getLocation())
                .build();
    }
}
