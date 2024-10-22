package com.microservices.rating.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservices.rating.service.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String>{
	
	@Query(value="SELECT * FROM MICRO_RATINGS WHERE USER_ID = :userId",nativeQuery=true)
	List<Rating> getAllRatingsByUserId(@Param("userId") String userId);
	@Query(value="SELECT * FROM MICRO_RATINGS WHERE HOTEL_ID = :hotelId",nativeQuery=true)
	List<Rating> getAllRatingsByHotelId(@Param("hotelId") String hotelId);
	
}
