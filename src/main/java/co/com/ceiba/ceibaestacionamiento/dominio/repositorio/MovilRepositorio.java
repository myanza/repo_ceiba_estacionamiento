package co.com.ceiba.ceibaestacionamiento.dominio.repositorio;

import java.util.Optional;

import co.com.ceiba.ceibaestacionamiento.persistencia.entidades.MovilEntity;

public interface MovilRepositorio 
{
	MovilEntity save(MovilEntity movilEntity);
	
	Optional<MovilEntity> findById(String placa);
}
