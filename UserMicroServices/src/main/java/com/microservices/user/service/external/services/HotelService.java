package com.microservices.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.user.service.entities.Hotel;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
