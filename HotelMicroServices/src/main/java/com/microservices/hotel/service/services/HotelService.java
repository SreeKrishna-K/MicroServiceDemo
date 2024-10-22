package com.microservices.hotel.service.services;

import java.util.List;

import com.microservices.hotel.service.entities.Hotel;

public interface HotelService {	
	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
}
