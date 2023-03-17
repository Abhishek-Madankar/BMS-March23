package com.example.Book_my_Seat_Application.Services;

import com.example.Book_my_Seat_Application.Convertors.UserConvertor;
import com.example.Book_my_Seat_Application.Entities.TicketEntity;
import com.example.Book_my_Seat_Application.Entities.UserEntity;
import com.example.Book_my_Seat_Application.EntryDtos.UserEntryDto;
import com.example.Book_my_Seat_Application.Repository.UserRepository;
import com.example.Book_my_Seat_Application.ResponseDto.TicketResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String adduser(UserEntryDto userEntryDto) throws Exception,NullPointerException{

        UserEntity userEntity = UserConvertor.convertDtoToEntity(userEntryDto);

        userRepository.save(userEntity);

        return "User Added Successfully";
    }


    public List<TicketResponse> getListOfTicket(int userId) throws Exception
    {
        if(userRepository.existsById(userId)) {
            List<TicketResponse> ticketResponseList =new ArrayList<>();
            List<TicketEntity> ticketList = userRepository.findById(userId).get().getBookedTickets();
            for(TicketEntity ticket : ticketList)
            {
                TicketResponse ticketResponse = TicketResponse.builder()
                        .movieName(ticket.getMovieName())
                        .theaterName(ticket.getTheaterName())
                        .showDate(ticket.getShowDate())
                        .showTime(ticket.getShowTime())
                        .totalAmount(ticket.getTotalAmount())
                        .bookedSeats(ticket.getBookedSeats())
                        .ticketId(ticket.getTicketId()).build();
                ticketResponseList.add(ticketResponse);
            }
            return ticketResponseList;
        }
        else
            throw new Exception("User Not Found");
    }

}
