package com.kairos.broker.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kairos.broker.model.EmpleadoDTO;

@RestController
@RequestMapping("/broker/develivery")
public class MainController {

	@PostMapping("/distribute/{id}")
	public ResponseEntity<?> ditribute( @PathVariable("id") Long id, @RequestBody EmpleadoDTO empleado ){
		
		RestTemplate resttemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String uri = "http://localhost:8080/api/v1/context/" + id;
		HttpEntity<EmpleadoDTO> request = new HttpEntity<>(empleado, headers);
		
		ResponseEntity<String> response =  resttemplate.exchange(uri, HttpMethod.PUT, request, String.class);
		System.out.println(response.getBody());
		
		return new ResponseEntity<>( "OK", HttpStatus.OK);
	}
	
}
