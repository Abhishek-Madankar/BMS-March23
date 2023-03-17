package com.example.Book_my_Seat_Application.Services;

import com.example.Book_my_Seat_Application.Convertors.TicketConvertor;
import com.example.Book_my_Seat_Application.Entities.ShowEntity;
import com.example.Book_my_Seat_Application.Entities.ShowSeatEntity;
import com.example.Book_my_Seat_Application.Entities.TicketEntity;
import com.example.Book_my_Seat_Application.Entities.UserEntity;
import com.example.Book_my_Seat_Application.EntryDtos.TicketEntryDto;
import com.example.Book_my_Seat_Application.Repository.ShowRepository;
import com.example.Book_my_Seat_Application.Repository.TicketRepository;
import com.example.Book_my_Seat_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception {
        TicketEntity ticketEntity = TicketConvertor.convertDtoToEntity(ticketEntryDto);
        //check the seat is available or not
        boolean isValidSeat = checkSeatIsBookedOrNot(ticketEntryDto);
        if (isValidSeat == false)
            throw new Exception(" Requsted Seat Not Available");
        //if seat available we need to calculate total price
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeats();

        int totalAmount = 0;

        for (ShowSeatEntity showSeatEntity : showSeatEntityList) {
            String seatNo = showSeatEntity.getSeatNo();
            if (requestedSeats.contains(seatNo)) {
                totalAmount += showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }
        ticketEntity.setTotalAmount(totalAmount);

        String movieName = showEntity.getMovieEntity().getMovieName();
        String theaterName = showEntity.getTheaterEntity().getName();

        ticketEntity.setMovieName(movieName);
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(theaterName);

        //set the show seats
        String allottedSeats = getAllottedSeatsFromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allottedSeats);

        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        ticketEntity = ticketRepository.save(ticketEntity);

        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);

        return "Ticket has Successfully added";
    }

    private String getAllottedSeatsFromShowSeats(List<String> allocatedSeats) {
        String bookedSeats = "";
        for (String seats : allocatedSeats) {
            bookedSeats += seats + ", ";
        }
        return bookedSeats;
    }

    private boolean checkSeatIsBookedOrNot(TicketEntryDto ticketEntryDto) {
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeats();

        for (ShowSeatEntity showSeatEntity : showSeatEntityList) {
            String seatNo = showSeatEntity.getSeatNo();
            if (requestedSeats.contains(seatNo)) {
                if (showSeatEntity.isBooked()) {
                    return false;
                }
            }
        }
        return true;
    }
}