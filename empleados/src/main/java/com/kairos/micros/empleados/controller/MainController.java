package com.kairos.micros.empleados.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kairos.micros.empleados.model.EmpleadoDTO;

@RestController
@RequestMapping("/api/v1")
public class MainController {
	
	/* Modificar el modelado de nuestros datos */
	private Long initId = 1L;
	private Map<Long, Set<EmpleadoDTO>> dataBase = new HashMap<>();

	@GetMapping("/departamento/{idDepto}/empleado")
	public ResponseEntity<?> getEmpleado(@PathVariable("idDepto") Long id, @RequestBody EmpleadoDTO empleadoDto) {
		Set<EmpleadoDTO> empleados = this.dataBase.get(id);
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}

	@PostMapping("/departamento/{idDepto}/empleado")
	public ResponseEntity<?> setEmpleado(@PathVariable("idDepto") Long id, @RequestBody EmpleadoDTO empleadoDto) {
		Set<EmpleadoDTO> empleados = this.dataBase.get(id);
		if( empleados == null ) {
			empleados = new HashSet<>();
			this.dataBase.put(id, empleados);
		}
		empleados.add(empleadoDto);
		
		RestTemplate resttemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String uri = "http://localhost:9090/broker/develivery/distribute/" + id;
		HttpEntity<EmpleadoDTO> request = new HttpEntity<>(empleadoDto, headers);
		
		ResponseEntity<String> response =  resttemplate.exchange(uri, HttpMethod.POST, request, String.class);
		System.out.println(response.getBody());
		
		return new ResponseEntity<>(empleadoDto, HttpStatus.CREATED);
	}

}
