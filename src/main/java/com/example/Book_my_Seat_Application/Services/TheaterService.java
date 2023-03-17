package com.example.Book_my_Seat_Application.Services;

import com.example.Book_my_Seat_Application.Convertors.TheaterConvertors;
import com.example.Book_my_Seat_Application.Entities.TheaterEntity;
import com.example.Book_my_Seat_Application.Entities.TheaterSeatEntity;
import com.example.Book_my_Seat_Application.EntryDtos.TheatreEntryDto;
import com.example.Book_my_Seat_Application.Enums.SeatType;
import com.example.Book_my_Seat_Application.Repository.TheaterSeatRepository;
import com.example.Book_my_Seat_Application.Repository.TheatreRepository;
import com.example.Book_my_Seat_Application.ResponseDto.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheatreRepository theatreRepository;
    public String addTheater(TheatreEntryDto theatreEntryDto) throws Exception{

        if(theatreEntryDto.getName() == null || theatreEntryDto.getLocation()==null){
            throw new Exception("Name and Location should be valid.");
        }
        //Converted TheaterDto to Theatre Entity
        TheaterEntity theaterEntity = TheaterConvertors.convertDtoToEntity(theatreEntryDto);

        List<TheaterSeatEntity> theaterSeatEntityList = createTheatreSeats(theatreEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntityList(createTheatreSeats(theatreEntryDto, theaterEntity));

        theatreRepository.save(theaterEntity);

        return "Theatre Added Successfully";
    }

    private List<TheaterSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto, TheaterEntity theaterEntity){
        int noClassicSeats = theatreEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theatreEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        //Created Classic Seats
        for(int count = 1; count<= noClassicSeats; count++){
            //we need to make a new TheatreSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //Created Premium Seats
        for(int count = 1; count<= noPremiumSeats; count++){
            //we need to make a new TheatreSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatType(SeatType.PREMIUM).seatNo(count+"P").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }


        return theaterSeatEntityList;
    }


    public List<TheaterResponse> Theaters()
    {

        List<TheaterResponse> theaterList = new ArrayList<>();
        List<TheaterEntity> theaterEntityList = theatreRepository.findAll();
        for(TheaterEntity theaterEntity: theaterEntityList)
        {
            TheaterResponse theaterResponse = TheaterResponse.builder()
                    .id(theaterEntity.getId())
                    .name(theaterEntity.getName())
                    .sizeOfTheater(theaterEntity.getTheaterSeatEntityList().size())
                    .build();
            theaterList.add(theaterResponse);
        }
        return theaterList;
    }


}
