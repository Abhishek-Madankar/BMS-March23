package com.example.Book_my_Seat_Application.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto {

    private String name;
    private String location;

    //This will be used to build theatre seats
    private int classicSeatsCount;
    private int premiumSeatsCount;

}
