package com.example.Book_my_Seat_Application.Services;

import com.example.Book_my_Seat_Application.Convertors.MovieConvertor;
import com.example.Book_my_Seat_Application.Entities.MovieEntity;
import com.example.Book_my_Seat_Application.EntryDtos.MovieEntryDto;
import com.example.Book_my_Seat_Application.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{

        MovieEntity movieEntity = MovieConvertor.ConvertEntryDtoToEntity(movieEntryDto);

        movieRepository.save(movieEntity);

        return "Movie Added Successfully";
    }
}
