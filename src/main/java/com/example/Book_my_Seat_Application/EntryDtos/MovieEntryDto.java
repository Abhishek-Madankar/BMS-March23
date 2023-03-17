package com.example.Book_my_Seat_Application.EntryDtos;

import com.example.Book_my_Seat_Application.Enums.Genre;
import com.example.Book_my_Seat_Application.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;

    private double ratings;

    private int duration;

    private Language language;

    private Genre genre;

}
