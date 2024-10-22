package com.microservices.user.service.services;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.service.entities.Hotel;
import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.external.services.HotelService;
import com.microservices.user.service.repositories.UserRepository;

import ch.qos.logback.classic.Logger;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
//		List<User> users = userRepository.findAllUsersWithRatingsAndHotels();
		List<User> users = userRepository.findAll();
//		users.stream().map();
		return users;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
		Rating[] user_ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId,Rating[].class);
		
//		ParameterizedTypeReference<ArrayList<Rating>> responseType = new ParameterizedTypeReference<ArrayList<Rating>>() {};
//		ArrayList<Rating> ratings = restTemplate.exchange("http://localhost:8083/ratings/users/" + userId, HttpMethod.GET, null, responseType).getBody();
		List<Rating> ratings = Arrays.stream(user_ratings).map(rating -> {
//			ResponseEntity<Hotel> forentity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = forentity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratings);
		return user;
	}

}
