package com.example.Book_my_Seat_Application.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponse {

    private int id;

    private String name;

    private int sizeOfTheater;

}
