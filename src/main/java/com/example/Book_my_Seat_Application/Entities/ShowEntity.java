package com.example.Book_my_Seat_Application.Entities;

import com.example.Book_my_Seat_Application.Enums.ShowType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    // This is Child with respect to movie Entity.
    //That One Movie will be having Many Shows. so. One to Many(From Movie to Show).
    // Show is child for Movie we will write mapping here
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;


    //This is Child Entity w.r.t Theater Entity
    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;

    //This is Parent class for Ticket Entity
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets = new ArrayList<>();

    //This is Parent Entity w.r.t showSeat Entity
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeats = new ArrayList<>();



}
