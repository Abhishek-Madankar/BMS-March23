package com.example.Book_my_Seat_Application.Services;

import com.example.Book_my_Seat_Application.Convertors.ShowConvertors;
import com.example.Book_my_Seat_Application.Entities.*;
import com.example.Book_my_Seat_Application.EntryDtos.ShowEntryDto;
import com.example.Book_my_Seat_Application.Enums.SeatType;
import com.example.Book_my_Seat_Application.Enums.ShowType;
import com.example.Book_my_Seat_Application.Repository.MovieRepository;
import com.example.Book_my_Seat_Application.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(ShowEntryDto showEntryDto){
        //create a show Entity from convertor.

        ShowEntity showEntity = ShowConvertors.convertEntryToEntity(showEntryDto);


        int movieId = showEntryDto.getMovieId();

        int theatreId = showEntryDto.getTheatreId();
        //setting the attributes of foreign key
        MovieEntity movieEntity = movieRepository.findById(movieId).get();

        TheaterEntity theaterEntity = theatreRepository.findById(theatreId).get();

        showEntity.setMovieEntity(movieEntity);

        showEntity.setTheaterEntity(theaterEntity);

        //Goal is to create showSeat Entity.

        List<ShowSeatEntity> seatEntityList = createShow(showEntryDto, showEntity);

        showEntity.setListOfShowSeats(seatEntityList);

        //Now we also need to update the parent entities.

        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();

        showEntityList.add(showEntity);

        movieEntity.setShowEntityList(showEntityList);
        movieRepository.save(movieEntity);

        List<ShowEntity> showEntityList1 = theaterEntity.getShowEntityList();
        showEntityList1.add(showEntity);
        theaterEntity.setShowEntityList(showEntityList1);

        theatreRepository.save(theaterEntity);

        return "The show has been added successfully";
    }

    private List<ShowSeatEntity> createShow (ShowEntryDto showEntryDto, ShowEntity showEntity){

        //Now the goal is to create show Seat Entity.

        TheaterEntity theaterEntity = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();

        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for (TheaterSeatEntity theaterSeatEntity: theaterSeatEntityList){

            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());

            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);

            showSeatEntity.setShowEntity(showEntity); //This is parent for showSeatEntity

            showSeatEntityList.add(showSeatEntity); //Adding to the list

        }

        return showSeatEntityList;
    }

    public String getShowByTheaterAndMovie(int theaterId, int movieId) throws Exception
    {
        if(theatreRepository.existsById(theaterId) && movieRepository.existsById(movieId))
        {
            TheaterEntity theaterEntity = theatreRepository.findById(theaterId).get();
            MovieEntity movieEntity = movieRepository.findById(movieId).get();

            //find the shows list at particular theater
            List<ShowEntity> showEntityList = theaterEntity.getShowEntityList();

            String ans= "Shows Available of Movie "+movieEntity.getMovieName()+" At Theater "+theaterEntity.getName()+" is \n";
            for(ShowEntity showEntity: showEntityList)
            {
                if (showEntity.getMovieEntity().equals(movieEntity))
                {
                    ans += "Date :"+showEntity.getShowDate()+" Time :"+showEntity.getShowTime()+"\n";
                }
            }
            return ans;
        }
        else
            throw new Exception("Invalid movie id or theater id");
    }

}
