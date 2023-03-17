package com.example.Book_my_Seat_Application.Controller;

import com.example.Book_my_Seat_Application.EntryDtos.UserEntryDto;
import com.example.Book_my_Seat_Application.ResponseDto.TicketResponse;
import com.example.Book_my_Seat_Application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> createUser(UserEntryDto userEntryDto){

        try{

            String response = userService.adduser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED );
        }
        catch (Exception e){

            String result = "User Could not be added";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listOfTickets")
    public ResponseEntity<List<TicketResponse>> getTickets(int userId)
    {
        try
        {
            List<TicketResponse> listOfTicket =userService.getListOfTicket(userId);
            return new ResponseEntity<>(listOfTicket, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            String message= e.getMessage();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        }
    }
}
