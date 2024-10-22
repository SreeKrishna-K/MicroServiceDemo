package com.microservices.user.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservices.user.service.entities.User;

public interface  UserRepository extends JpaRepository<User, String> 
{	
	@Query(value = 
            "SELECT " +
            "    u.ID as userId, " +
            "    u.NAME as name, " +
            "    u.EMAIL as email, " +
            "    u.ABOUT as about, " +
            "    r.RATING_ID as ratingId, " +
            "    r.FEEDBACK as feedback, " +
            "    r.RATING as rating, " +
            "    h.ID as hotelId, " +
            "    h.NAME as hotelName, " +
            "    h.LOCATION as hotelLocation, " +
            "    h.ABOUT as hotelAbout " +
            "FROM " +
            "    micro_users u " +
            "LEFT JOIN " +
            "    micro_ratings r ON u.ID = r.USER_ID " +
            "LEFT JOIN " +
            "    micro_hotels h ON r.HOTEL_ID = h.ID",
            nativeQuery = true)
     List<User> findAllUsersWithRatingsAndHotels();
}
