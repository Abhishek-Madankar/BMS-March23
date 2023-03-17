package com.example.Book_my_Seat_Application.Controller;

import com.example.Book_my_Seat_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_my_Seat_Application.ResponseDto.TheaterResponse;
import com.example.Book_my_Seat_Application.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(TheatreEntryDto theatreEntryDto)throws Exception{
        try {
            String response = theaterService.addTheater(theatreEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/noOfTheaters")
    public List<TheaterResponse> getNoOfTheaters()
    {
        return theaterService.Theaters();
    }


}
