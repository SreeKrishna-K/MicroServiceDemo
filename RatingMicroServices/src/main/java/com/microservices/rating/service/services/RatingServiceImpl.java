package com.microservices.rating.service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.rating.service.entities.Rating;
import com.microservices.rating.service.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getAllRatingsByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllRatingsByHotelId(hotelId);
	}

	@Override
	public List<Rating> getAllRatingsByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllRatingsByUserId(userId);
	}

}
