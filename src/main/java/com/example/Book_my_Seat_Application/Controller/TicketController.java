package com.example.Book_my_Seat_Application.Controller;

import com.example.Book_my_Seat_Application.EntryDtos.TicketEntryDto;
import com.example.Book_my_Seat_Application.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(TicketEntryDto ticketEntryDto){

        try {
            String message= ticketService.addTicket(ticketEntryDto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            String error= e.getMessage();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
