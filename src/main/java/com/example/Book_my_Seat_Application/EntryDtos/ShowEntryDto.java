package com.example.Book_my_Seat_Application.EntryDtos;

import com.example.Book_my_Seat_Application.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ShowEntryDto {

    private LocalDate localDate;

    private LocalTime localTime;

    private ShowType showType;

    private int movieId;

    private int theatreId;

    private int classicSeatPrice;

    private int premiumSeatPrice;

}
