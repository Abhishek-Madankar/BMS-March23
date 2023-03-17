package com.example.Book_my_Seat_Application.Repository;

import com.example.Book_my_Seat_Application.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
