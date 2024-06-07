package com.ista.usuario.dao;

import org.springframework.data.repository.CrudRepository;

import com.ista.usuario.entity.Empleado;

public interface EmpleadoDao extends CrudRepository<Empleado, Long> {

}
