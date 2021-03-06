package com.kairos.micros.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.micros.model.DepartamentoDTO;
import com.kairos.micros.model.EmpleadoDTO;

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
		this.initId++;
		this.dataBase.put(initId, departamentoDTO);
		return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
	}

	@GetMapping("/departamento/{idDepto}/empleado")
	public ResponseEntity<?> getEmpleado(@PathVariable("idDepto") Long id, @RequestBody EmpleadoDTO empleadoDto) {
		DepartamentoDTO depto = this.dataBase.get(id);
		return new ResponseEntity<>(depto.getEmpleados(), HttpStatus.OK);
	}

	@PostMapping("/departamento/{idDepto}/empleado")
	public ResponseEntity<?> setEmpleado(@PathVariable("idDepto") Long id, @RequestBody EmpleadoDTO empleadoDto) {
		DepartamentoDTO depto = this.dataBase.get(id);
		Set<EmpleadoDTO> empleados = depto.getEmpleados();
		empleados.add(empleadoDto);
		return new ResponseEntity<>(empleadoDto, HttpStatus.CREATED);
	}

}
