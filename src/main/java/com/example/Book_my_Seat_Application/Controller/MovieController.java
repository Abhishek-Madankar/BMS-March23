package com.example.Book_my_Seat_Application.Controller;

import com.example.Book_my_Seat_Application.EntryDtos.MovieEntryDto;
import com.example.Book_my_Seat_Application.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovies(@RequestBody MovieEntryDto movieEntryDto){

        try {
            String response = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED );
        }
        catch (Exception e){
            String error = "Movie not added";
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
