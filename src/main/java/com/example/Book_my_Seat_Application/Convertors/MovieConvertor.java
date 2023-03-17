package com.example.Book_my_Seat_Application.Convertors;

import com.example.Book_my_Seat_Application.Entities.MovieEntity;
import com.example.Book_my_Seat_Application.EntryDtos.MovieEntryDto;
import lombok.Data;


public class MovieConvertor {

    public static MovieEntity ConvertEntryDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName())
                .genre(movieEntryDto.getGenre()).duration(movieEntryDto.getDuration())
                .language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings())
                .build();

        return movieEntity;
    }

}
