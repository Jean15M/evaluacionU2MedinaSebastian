package com.ista.usuario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ista.usuario.entity.Empleado;
import com.ista.usuario.services.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/empleado")
	public List<Empleado> index() {
		return empleadoService.findAll();
	}

	@GetMapping("/empleado/{id}")
	public ResponseEntity<Empleado> show(@PathVariable Long id) {
		Empleado listaEncontrada = empleadoService.findbyId(id);
		if(listaEncontrada==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(listaEncontrada);
	}

	@PostMapping("/empleado")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Empleado> create(@RequestBody Empleado empleado) {
		if(empleado.getApellido().isEmpty() || empleado.getNombre().isEmpty() || empleado.getDireccion().isEmpty() || empleado.getObservacion().isEmpty() || empleado.getSueldo()<0 || empleado.getFecha_nacimiento() == null || empleado.getDias_trabajo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Long dias = empleado.getDias_trabajo();
		if(dias>=20) {
			double sueldo = dias * 15;
			double bono = sueldo * 0.02 + sueldo;
			empleado.setSueldo(bono);
		}
		
		if(dias>=30) {
			double sueldo = dias * 15;
			double bono = sueldo * 0.05 + sueldo;
			empleado.setSueldo(bono);
		}else {
			double sueldo = dias * 15;
			empleado.setSueldo(sueldo);
		}
		Empleado listaGuardada = empleadoService.save(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(listaGuardada);
		
	}

	@PutMapping("/empleado/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Empleado> update(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado listaActual = empleadoService.findbyId(id);
		if(listaActual==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		if(empleado.getApellido().isEmpty() || empleado.getNombre().isEmpty() || empleado.getDireccion().isEmpty() || empleado.getObservacion().isEmpty() || empleado.getDias_trabajo() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		listaActual.setApellido(empleado.getApellido());
		listaActual.setNombre(empleado.getNombre());
		listaActual.setObservacion(empleado.getObservacion());
		listaActual.setTelefono(empleado.getTelefono());
		listaActual.setDias_trabajo(empleado.getDias_trabajo());
		Long dias = empleado.getDias_trabajo();
		if(dias>=20) {
			double sueldo = dias * 15;
			double bono = sueldo * 0.02 + sueldo;
			listaActual.setSueldo(bono);
		}
		
		if(dias>=30) {
			double sueldo = dias * 15;
			double bono = sueldo * 0.05 + sueldo;
			listaActual.setSueldo(bono);
		}else {
			double sueldo = dias * 15;
			listaActual.setSueldo(sueldo);
		}
		Empleado listaGuardada = empleadoService.save(listaActual);
		return ResponseEntity.ok(listaGuardada);
	}

	@DeleteMapping("/empleado/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Empleado listaEncontrada = empleadoService.findbyId(id);
		if(listaEncontrada==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		empleadoService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
