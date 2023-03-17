package com.example.Book_my_Seat_Application.Controller;

import com.example.Book_my_Seat_Application.EntryDtos.ShowEntryDto;
import com.example.Book_my_Seat_Application.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        try {
            String message=showService.addShow(showEntryDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            String error=e.getMessage();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getShow")
    public ResponseEntity<String> getShowByTheaterAndMovie(@RequestParam("theaterId") int theaterId,@RequestParam("movieId") int movieId)
    {
        try{
            String message= showService.getShowByTheaterAndMovie(theaterId,movieId);
            return new ResponseEntity<>(message, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            String message= e.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

}
