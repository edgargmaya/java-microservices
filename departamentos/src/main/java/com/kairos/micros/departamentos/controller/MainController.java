package com.kairos.micros.departamentos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.micros.departamentos.model.DepartamentoDTO;
import com.kairos.micros.departamentos.model.EmpleadoDTO;

@RestController
@RequestMapping("/api/v1")
public class MainController {

	private Long initId = 1L;
	private Map<Long, DepartamentoDTO> dataBase = new HashMap<>();

	@GetMapping("/departamento")
	public ResponseEntity<?> getDepartamento() {
		List<?> demoList = new ArrayList<>(this.dataBase.values());
		return new ResponseEntity<>(demoList, HttpStatus.OK);
	}

	@PostMapping("/departamento")
	public ResponseEntity<?> setDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
		departamentoDTO.setId(initId);
		this.dataBase.put(initId, departamentoDTO);
		this.initId++;
		return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/context/{id}")
	public ResponseEntity<?> updateContext(@PathVariable("id") Long id, @RequestBody EmpleadoDTO empleado) {
		DepartamentoDTO depto = this.dataBase.get(id);
		Set<EmpleadoDTO> empleados =  depto.getEmpleados();
		if( empleados == null ) {
			empleados = new HashSet<EmpleadoDTO>();
		}
		empleados.add(empleado);
		return new ResponseEntity<>("Context updated...", HttpStatus.OK);
	}

}
