package com.ista.usuario.services;

import java.util.List;

import com.ista.usuario.entity.Empleado;


public interface EmpleadoService {

	public List<Empleado> findAll();

	public Empleado save(Empleado empleado);

	public Empleado findbyId(Long id);

	public void delete(Long id);
}
