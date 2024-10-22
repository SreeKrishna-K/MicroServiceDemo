package com.microservices.rating.service.services;

import java.util.List;

import com.microservices.rating.service.entities.Rating;

public interface RatingService {
	Rating create(Rating rating);
	List<Rating> getAllRatings();
	List<Rating> getAllRatingsByHotelId(String hotelId);
	List<Rating> getAllRatingsByUserId(String userId);
}
