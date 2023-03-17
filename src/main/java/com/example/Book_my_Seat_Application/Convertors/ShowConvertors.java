package com.example.Book_my_Seat_Application.Convertors;

import com.example.Book_my_Seat_Application.Entities.ShowEntity;
import com.example.Book_my_Seat_Application.EntryDtos.ShowEntryDto;

public class ShowConvertors {

    public static ShowEntity convertEntryToEntity(ShowEntryDto showEntryDto){

        ShowEntity showEntity = ShowEntity.builder().showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime()).showType(showEntryDto.getShowType())
                .build();

        return showEntity;
    }
}
