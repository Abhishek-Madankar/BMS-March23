package com.example.Book_my_Seat_Application.Convertors;

import com.example.Book_my_Seat_Application.Entities.TicketEntity;
import com.example.Book_my_Seat_Application.EntryDtos.TicketEntryDto;

public class TicketConvertor {

    public static TicketEntity convertDtoToEntity(TicketEntryDto ticketEntryDto)
    {
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;
    }

}
